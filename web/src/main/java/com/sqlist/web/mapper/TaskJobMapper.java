package com.sqlist.web.mapper;

import com.sqlist.web.domain.TaskUnitJob;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/6 0:41
 * @description
 **/
public interface TaskJobMapper {

    /**
     * 选取 启动中 的 job
     * @param tid
     * @return
     */
    List<TaskUnitJob> selectStartingJob(Integer tid);
}
