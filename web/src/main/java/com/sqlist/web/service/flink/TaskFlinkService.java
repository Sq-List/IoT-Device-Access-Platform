package com.sqlist.web.service.flink;

import com.sqlist.web.domain.Task;

/**
 * @author SqList
 * @date 2019/5/4 22:14
 * @description
 **/
public interface TaskFlinkService {

    /**
     * 启动任务
     * @param task
     */
    void start(Task task);

    /**
     * 停止任务
     * @param task
     */
    void stop(Task task);
}
