package com.sqlist.admin.controller;

import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.service.ProductService;
import com.sqlist.admin.service.UserService;
import com.sqlist.admin.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author SqList
 * @date 2019/5/12 22:38
 * @description
 **/
@RestController
@RequestMapping("/api/overview")
public class OverviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/nums", method = RequestMethod.GET)
    public Result nums() {
        HashMap<String, Integer> numMap = new HashMap<>(5);
        numMap.put("userNum", userService.count());
        numMap.put("productNum", productService.count());
        numMap.put("deviceNum", deviceService.count());
        numMap.put("taskNum", taskService.count());

        return Result.success(numMap);
    }

    @RequestMapping("/user/online/num")
    public Result onlineUserNum() {
        return Result.success(userService.countOnline());
    }

    @RequestMapping("/device/online/num")
    public Result onlineDeviceNum() {
        return Result.success(deviceService.countOnline());
    }

    @RequestMapping("/task/running/num")
    public Result runningTaskNum() {
        return Result.success(taskService.countRunning());
    }
}
