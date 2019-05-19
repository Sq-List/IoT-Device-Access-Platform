package com.sqlist.clientweb.service.impl;

import com.sqlist.clientweb.domain.TemperatureAlarm;
import com.sqlist.clientweb.mapper.TemperatureAlarmMapper;
import com.sqlist.clientweb.service.TemperatureAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/19 23:48
 * @description
 **/
@Service
public class TemperatureAlarmServiceImpl implements TemperatureAlarmService {

    @Autowired
    private TemperatureAlarmMapper temperatureAlarmMapper;

    @Override
    public void add(TemperatureAlarm temperatureAlarm) {
        temperatureAlarmMapper.insert(temperatureAlarm);
    }

    @Override
    public Integer getAlarmIsTrue() {
        TemperatureAlarm temperatureAlarm = new TemperatureAlarm();
        temperatureAlarm.setAlarm(true);
        return temperatureAlarmMapper.selectCount(temperatureAlarm);
    }
}
