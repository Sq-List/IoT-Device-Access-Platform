package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.Task;
import com.sqlist.admin.domain.TaskSendSum;
import com.sqlist.admin.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskSendSumMapper extends MyMapper<TaskSendSum> {

    /**
     * 根据时间范围获取某任务发送量
     * @param task
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> selectCountBetweenTime(@Param("task") Task task, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 获取 近7天 每天 该任务发送量
     * @param task
     * @return
     */
    List<Map<String, Object>> selectCountPerDayIn7Day(Task task);

    /**
     * 获取 该任务总发送量
     * @param task
     * @return
     */
    Map<String, Object> selectCountAll(Task task);
}