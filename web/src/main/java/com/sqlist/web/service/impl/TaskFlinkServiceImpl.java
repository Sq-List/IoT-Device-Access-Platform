package com.sqlist.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sqlist.web.domain.TaskUnitHandle;
import com.sqlist.web.domain.TaskUnitInput;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.FileService;
import com.sqlist.web.service.TaskFlinkService;
import com.sqlist.web.service.TaskUnitHandleService;
import com.sqlist.web.service.TaskUnitInputService;
import com.sqlist.web.util.StreamUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author SqList
 * @date 2019/5/5 1:08
 * @description
 **/
@Slf4j
@Service
public class TaskFlinkServiceImpl implements TaskFlinkService {

    public static final String SUCCESS = "success";
    public static final String STATUS = "status";

    @Value("http://${config.flink.rest.ip}")
    private String flinkIp;

    @Autowired
    private FileService fileService;

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    private void upload(Integer fid, File file) throws IOException {
        String url = flinkIp + "/jars/upload";
        Response response = OkHttpUtils.post()
                                    .addFile("jarfile", file.getName(), file)
                                    .url(url)
                                    .addHeader("Content-Type", "application/octet-stream")
                                    .build()
                                    .execute();
        String resString = response.body().string();
        log.debug("response: {}", resString);

        JSONObject resJson = JSON.parseObject(resString);
        if (SUCCESS.equals(resJson.getString(STATUS))) {
            String filePath = resJson.getString("filename");
            String jarId = new File(filePath).getName();
            log.info("jarId: {}", jarId);

            fileService.updateJarId(fid, jarId);
        }
    }

    private void upload(Integer fid, String filePath) throws IOException {
        File file = new File(filePath);
        upload(fid, file);
    }

    @Async
    @Override
    public void start(Integer tid) {
        TaskUnitInput input = taskUnitInputService.list(tid).get(0);
        List<TaskUnitHandle> handleList = taskUnitHandleService.list(tid);

        // 检查 该任务下 所有 handle 对应的 file jar 是否已经全部上传到 flink
        handleList.parallelStream()
                .filter(StreamUtil.distinctByKey(TaskUnitHandle::getFid))
                .forEach(taskUnitHandle -> {
                    com.sqlist.web.domain.File file = fileService.get(taskUnitHandle.getFid());
                    if ("".equals(file.getJarId())) {
                        try {
                            upload(file.getFid(), file.getPath());
                        } catch (IOException e) {
                            log.error("IOException, file: {}", file);
                            e.printStackTrace();
                        }
                    }
                });


    }

    @Async
    @Override
    public void stop(Integer tid) {

    }
}
