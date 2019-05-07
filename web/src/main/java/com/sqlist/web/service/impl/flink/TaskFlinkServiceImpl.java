package com.sqlist.web.service.impl.flink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sqlist.web.constants.TaskJobStatus;
import com.sqlist.web.domain.*;
import com.sqlist.web.service.*;
import com.sqlist.web.service.flink.TaskFlinkService;
import com.sqlist.web.service.task.*;
import com.zhy.http.okhttp.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SqList
 * @date 2019/5/5 1:08
 * @description
 **/
@Slf4j
@Service
public class TaskFlinkServiceImpl implements TaskFlinkService {

    public static final String JAVA = "java";

    @Value("http://${config.flink.rest.ip}:${config.flink.rest.port}")
    private String flinkIp;

    @Value("${config.kafka.ip}:${config.kafka.port}")
    private String kafkaIp;

    @Value("${config.zookeeper.ip}:${config.zookeeper.port}")
    private String zookeeperIp;

    @Value("${config.jar.mainClass}")
    private String mainClass;

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @Autowired
    private CommonJarService commonJarService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TaskJobService taskJobService;

    @Transactional(rollbackFor = RuntimeException.class)
    public void inputToOutput(TaskUnitInput input, TaskUnitOutput output) throws IOException {
        Product product = productService.get(input.getPid());
        toOutput(product.getTopic(), output);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void inputToHandle(TaskUnitInput input, TaskUnitHandle handle) throws IOException {
        Product product = productService.get(input.getPid());

        String url = "";
        Map<String, String> paramsMap = new HashMap<>();
        if (File.JAR.equals(handle.getType())) {
            File file = fileService.get(handle.getFid());
            String jarId = file.getJarId();
            url = flinkIp + "/jars/" + jarId + "/run";

            StringBuilder paramsArgs = new StringBuilder();
            paramsArgs.append("--consumerIp ").append(kafkaIp)
                    .append(" --consumerTopic ").append(product.getTopic())
                    .append(" --consumerGroup ").append(handle.getTuid()).append("-group")
                    .append(" --consumerZookeeperIp ").append(zookeeperIp)
                    .append(" --producerIp ").append(kafkaIp)
                    .append(" --producerTopic ").append(handle.getTuid())
                    .append(" --implementClass ").append(file.getImplementClass());

            paramsMap.put("programArgs", paramsArgs.toString());
            paramsMap.put("entryClass", file.getMainClass());

        } else if (File.LUA.equals(handle.getType())) {
            CommonJar commonJar = commonJarService.get(TaskUnit.HANDLE, handle.getType());
            String jarId = commonJar.getJarId();
            File file = fileService.get(handle.getFid());
            url = flinkIp + "/jars/" + jarId + "/run";

            StringBuilder paramsArgs = new StringBuilder();
            paramsArgs.append("--consumerIp ").append(kafkaIp)
                    .append(" --consumerTopic ").append(product.getTopic())
                    .append(" --consumerGroup ").append(handle.getTuid()).append("-group")
                    .append(" --consumerZookeeperIp ").append(zookeeperIp)
                    .append(" --producerIp ").append(kafkaIp)
                    .append(" --producerTopic ").append(handle.getTuid())
                    .append(" --luaFilePath ").append(file.getJarId());

            paramsMap.put("programArgs", paramsArgs.toString());
            paramsMap.put("entryClass", commonJar.getMainClass());
        }

        String jobid = request(url, paramsMap);
        handle.setJobId(jobid);
        handle.setStatus(TaskJobStatus.STARTING.name());
        taskUnitHandleService.update(handle);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void handleToOutput(TaskUnitHandle handle, TaskUnitOutput output) throws IOException {
        toOutput(handle.getTuid(), output);
    }

    private void toOutput(String topic, TaskUnitOutput output) throws IOException {
        CommonJar commonJar = commonJarService.get(TaskUnit.OUTPUT, output.getType());
        String jarId = commonJar.getJarId();
        String mainClass = commonJar.getMainClass();
        String url = flinkIp + "/jars/" + jarId + "/run";

        StringBuilder paramsArgs = new StringBuilder();
        paramsArgs.append("--consumerIp ").append(kafkaIp)
                .append(" --consumerTopic ").append(topic)
                .append(" --consumerGroup ").append(output.getTuid()).append("-group")
                .append(" --consumerZookeeperIp ").append(zookeeperIp)
                .append(" --producerIp ").append(output.getIp()).append(":").append(output.getPort())
                .append(" --producerTopic ").append(output.getUrl());

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("programArgs", paramsArgs.toString());
        paramsMap.put("entryClass", mainClass);

        String jobid = request(url, paramsMap);
        output.setJobId(jobid);
        output.setStatus(TaskJobStatus.STARTING.name());
        taskUnitOutputService.update(output);
    }

    private String request(String url, Map<String, String> paramsMap) throws IOException {
        log.info("request: {}", url);
        String paramsString = JSON.toJSONString(paramsMap);
        log.debug("params: {}", paramsString);
        Response response = OkHttpUtils.postString()
                .content(paramsString)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(url)
                .build()
                .execute();

        String resString = response.body().string();
        log.debug("response: {}", resString);

        JSONObject resJson = JSON.parseObject(resString);
        return resJson.getString("jobid");
    }

    @Async
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void start(Integer tid) {
        TaskUnitInput input = taskUnitInputService.list(tid).get(0);
        Map<String, TaskUnitHandle> tuidToHandleMap = taskUnitHandleService.list(tid)
                                                        .stream()
                                                        .collect(Collectors.toMap(TaskUnit::getTuid, handle -> handle));
        Map<String, TaskUnitOutput> tuidToOutputMap = taskUnitOutputService.list(tid)
                                                        .stream()
                                                        .collect(Collectors.toMap(TaskUnit::getTuid, output -> output));

        List<TaskUnitConnect> connectList = taskUnitConnectService.list(tid);
        connectList.forEach(connect -> {
            String sourceTuid = connect.getSourceTuid();
            String targetTuid = connect.getTargetTuid();

            try {
                if (sourceTuid.startsWith(TaskUnit.INPUT) && targetTuid.startsWith(TaskUnit.HANDLE)) {
                    inputToHandle(input, tuidToHandleMap.get(targetTuid));
                } else if (sourceTuid.startsWith(TaskUnit.HANDLE) && targetTuid.startsWith(TaskUnit.OUTPUT)) {
                    handleToOutput(tuidToHandleMap.get(sourceTuid), tuidToOutputMap.get(targetTuid));
                } else if (sourceTuid.startsWith(TaskUnit.INPUT) && targetTuid.startsWith(TaskUnit.OUTPUT)) {
                    inputToOutput(input, tuidToOutputMap.get(targetTuid));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Async
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void stop(Integer tid) {
        List<TaskUnitJob> taskUnitJobList = new ArrayList<>();
        taskUnitJobList.addAll(taskUnitOutputService.list(tid));
        taskUnitJobList.addAll(taskUnitHandleService.list(tid));

        for (TaskUnitJob job : taskUnitJobList) {
            String url = flinkIp + "/jobs/" + job.getJobId() + "/yarn-cancel";
            log.info("request: {}", url);
            try {
                Response response = OkHttpUtils.get()
                                        .url(url)
                                        .build()
                                        .execute();
                String resString = response.body().string();
                log.debug("response: {}", resString);
            } catch (IOException e) {
                e.printStackTrace();
            }

            job.setJobId("");
            job.setStatus(TaskJobStatus.UNUSE.name());
            taskJobService.update(job);
        }
    }
}
