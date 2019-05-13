package com.sqlist.web.controller;

import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.DeviceService;
import com.sqlist.web.service.FileService;
import com.sqlist.web.service.ProductService;
import com.sqlist.web.service.TaskSendSumService;
import com.sqlist.web.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author SqList
 * @date 2019/5/13 1:43
 * @description
 **/
@RestController
@RequestMapping("/api/overview")
public class OverviewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TaskSendSumService taskSendSumService;

    @RequestMapping(value = "/nums", method = RequestMethod.GET)
    public Result nums(User user) {
        HashMap<String, Integer> numsMap = new HashMap<>(6);
        numsMap.put("productNum", productService.count(user));
        numsMap.put("deviceNum", deviceService.count(user));
        numsMap.put("taskNum", taskService.count(user));
        numsMap.put("fileNum", fileService.count(user));

        return Result.success(numsMap);
    }

    @RequestMapping(value = "/24hour", method = RequestMethod.GET)
    public Result get24HourInfo(User user) {
        return Result.success(taskSendSumService.get24HourInfo(user));
    }

    @RequestMapping(value = "/7day", method = RequestMethod.GET)
    public Result get7DayInfo(User user) {
        return Result.success(taskSendSumService.get7DayInfo(user));
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET)
    public Result getTotalInfo(User user) {
        return Result.success(taskSendSumService.getTotalInfo(user));
    }
}
