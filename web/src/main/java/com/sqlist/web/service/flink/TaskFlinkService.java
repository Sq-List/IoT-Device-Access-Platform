package com.sqlist.web.service.flink;

/**
 * @author SqList
 * @date 2019/5/4 22:14
 * @description
 **/
public interface TaskFlinkService {

    /**
     * 启动任务
     * @param tid
     */
    void start(Integer tid);

    /**
     * 停止任务
     * @param tid
     */
    void stop(Integer tid);
}
