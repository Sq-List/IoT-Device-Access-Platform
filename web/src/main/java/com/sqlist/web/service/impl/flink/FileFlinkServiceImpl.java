package com.sqlist.web.service.impl.flink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sqlist.web.service.flink.FileFlinkService;
import com.zhy.http.okhttp.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author SqList
 * @date 2019/5/5 22:31
 * @description
 **/
@Slf4j
@Service
public class FileFlinkServiceImpl implements FileFlinkService {

    public static final String SUCCESS = "success";
    public static final String STATUS = "status";

    @Value("http://${config.flink.rest.ip}")
    private String flinkIp;

    @Override
    public String upload(File file) throws IOException {
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

            return jarId;
        }
        return null;
    }

    @Override
    public String upload(String filePath) throws IOException {
        File file = new File(filePath);
        return upload(file);
    }
}
