package com.sqlist.web.controller;

import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskUnitOutputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/1 1:37
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/taskUnitOutput")
public class TaskUnitOutputController {

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @RequestMapping(value = "/{tuid}", method = RequestMethod.GET)
    public Result get(@PathVariable("tuid") String tuid) {
        return Result.success(taskUnitOutputService.get(tuid));
    }
}
