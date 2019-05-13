package com.sqlist.web.mapper;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.TaskSendSum;
import com.sqlist.web.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface TaskSendSumMapper extends MyMapper<TaskSendSum> {

    /**
     * 获取 近24小时 每小时 该任务发送量
     * @param task
     * @return
     */
    List<Map<String, Object>> selectCountPerHourIn24Hours(Task task);

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