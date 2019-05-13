package com.sqlist.web.service;

import com.sqlist.web.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/13 23:46
 * @description
 **/
public interface TaskSendSumService {

    /**
     * 获取 近24小时 每小时 用户各个任务发送量
     * @param user
     * @return
     */
    Map get24HourInfo(User user);

    /**
     * 获取 近7天 每天 该任务发送量
     * @param user
     * @return
     */
    Map get7DayInfo(User user);

    /**
     * 获取 该任务总发送量
     * @param user
     * @return
     */
    Map getTotalInfo(User user);
}
