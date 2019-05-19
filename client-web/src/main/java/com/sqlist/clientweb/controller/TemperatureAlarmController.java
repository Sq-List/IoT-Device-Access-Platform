package com.sqlist.clientweb.controller;

import com.sqlist.clientweb.result.Result;
import com.sqlist.clientweb.service.TemperatureAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/19 21:15
 * @description
 **/
@RestController
@RequestMapping("/api/temperatureAlarm")
public class TemperatureAlarmController {

    @Autowired
    private TemperatureAlarmService temperatureAlarmService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result list() {
        return Result.success(temperatureAlarmService.getAlarmIsTrue());
    }
}
