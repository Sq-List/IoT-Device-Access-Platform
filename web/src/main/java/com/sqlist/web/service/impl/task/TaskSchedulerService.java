package com.sqlist.web.service.impl.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sqlist.web.constants.TaskJobStatus;
import com.sqlist.web.constants.TaskStatus;
import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.TaskUnitJob;
import com.sqlist.web.service.task.TaskJobService;
import com.sqlist.web.service.task.TaskService;
import com.zhy.http.okhttp.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author SqList
 * @date 2019/5/5 23:57
 * @description
 **/
@Slf4j
@Service
public class TaskSchedulerService {

    @Value("http://${config.flink.rest.ip}:${config.flink.rest.port}")
    private String flinkIp;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskJobService taskJobService;

    @Scheduled(fixedDelay = 5000)
    @Transactional(rollbackFor = RuntimeException.class)
    public void isRunning() {
        log.debug("isRunning()");
        List<Task> taskList = taskService.getStartingTask();
        if (taskList.size() != 0) {
            for (Task task : taskList) {
                List<TaskUnitJob> startingJobList = taskJobService.getStartingJob(task.getTid());
                boolean running = true;
                for (TaskUnitJob job : startingJobList) {
                    String url = flinkIp + "/jobs/" + job.getJobId();
                    try {
                        Response response = OkHttpUtils.get()
                                .url(url)
                                .build()
                                .execute();

                        String resString = response.body().string();
                        log.debug("isRunning(), response: {}", resString);

                        JSONObject resJson = JSON.parseObject(resString);
                        String status = resJson.getString("state");
                        if (status.equals(TaskJobStatus.RUNNING.name())) {
                            job.setStatus(TaskJobStatus.RUNNING.name());
                            taskJobService.update(job);
                        } else {
                            running = false;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // 所有 任务单元 都是运行状态
                if (running) {
                    task.setStatus(TaskStatus.RUNNING.name());
                    taskService.update(task);
                }
            }
        }
    }
}
