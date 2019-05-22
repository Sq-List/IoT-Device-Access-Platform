package com.sqlist.web.service;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/13 23:46
 * @description
 **/
public interface TaskSendSumService {

    /**
     * 根据时间获取发送量
     * @param task
     * @param startTime
     * @param endTime
     * @return
     */
    Map getInfoBetweenTime(Task task, Date startTime, Date endTime);

    /**
     * 获取 近7天 每天 该任务发送量
     * @param task
     * @return
     */
    Map get7DayInfo(Task task);

    /**
     * 获取 该任务总发送量
     * @param task
     * @return
     */
    Map getTotalInfo(Task task);
}
