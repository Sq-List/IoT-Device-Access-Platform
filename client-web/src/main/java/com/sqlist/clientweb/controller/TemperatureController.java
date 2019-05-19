package com.sqlist.clientweb.controller;

import com.sqlist.clientweb.result.Result;
import com.sqlist.clientweb.service.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/19 21:15
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {
        return Result.success(temperatureService.list());
    }
}
