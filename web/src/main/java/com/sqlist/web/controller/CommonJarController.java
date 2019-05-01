package com.sqlist.web.controller;

import com.sqlist.web.result.Result;
import com.sqlist.web.service.CommonJarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/2 2:15
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/commonJar")
public class CommonJarController {

    @Autowired
    private CommonJarService commonJarService;

    @RequestMapping(value = "/{taskUnitType}/types", method = RequestMethod.GET)
    public Result getType(@PathVariable("taskUnitType") String taskUnitType) {
        return Result.success(commonJarService.getType(taskUnitType));
    }
}
