package com.sqlist.web.controller;

import com.sqlist.web.result.Result;
import com.sqlist.web.service.task.TaskUnitInputService;
import com.sqlist.web.vo.TaskUnitInputVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author SqList
 * @date 2019/5/1 1:36
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/taskUnitInput")
public class TaskUnitInputController {

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @RequestMapping(value = "/{tuid}", method = RequestMethod.GET)
    public Result get(@PathVariable("tuid") String tuid) {
        return Result.success(taskUnitInputService.get(tuid));
    }

    @RequestMapping(value = "/{tuid}", method = RequestMethod.POST)
    public Result updateDetail(@PathVariable("tuid") String tuid, @Validated @RequestBody TaskUnitInputVO taskUnitInputVO) {
        taskUnitInputVO.setTuid(tuid);
        taskUnitInputService.updateDetail(taskUnitInputVO);
        return Result.success(null);
    }
}
