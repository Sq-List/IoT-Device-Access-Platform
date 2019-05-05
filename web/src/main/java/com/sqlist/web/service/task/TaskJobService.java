package com.sqlist.web.service.task;

import com.sqlist.web.domain.TaskUnitJob;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/6 0:31
 * @description
 **/
public interface TaskJobService {

    /**
     * 获取 启动中 的job
     * @param tid
     * @return
     */
    List<TaskUnitJob> getStartingJob(Integer tid);

    /**
     * 更新
     * @param taskUnitJob
     */
    void update(TaskUnitJob taskUnitJob);
}