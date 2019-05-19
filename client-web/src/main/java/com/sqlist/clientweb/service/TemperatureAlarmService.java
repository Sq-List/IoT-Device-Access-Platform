package com.sqlist.clientweb.service;

import com.sqlist.clientweb.domain.TemperatureAlarm;


/**
 * @author SqList
 * @date 2019/5/19 21:14
 * @description
 **/
public interface TemperatureAlarmService {

    /**
     * 添加
     * @param temperatureAlarm
     */
    void add(TemperatureAlarm temperatureAlarm);

    /**
     * 获取报警次数
     * @return
     */
    Integer getAlarmIsTrue();
}
