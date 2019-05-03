package com.sqlist.web.controller;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskUnitHandleService;
import com.sqlist.web.service.TaskUnitInputService;
import com.sqlist.web.service.TaskUnitOutputService;
import com.sqlist.web.service.TaskUnitService;
import com.sqlist.web.vo.TaskUnitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/22 14:39
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/taskUnit")
public class TaskUnitController {

    @Autowired
    private TaskUnitService taskUnitService;

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @RequestMapping(value = "/{tid}", method = RequestMethod.GET)
    public Result list(User user, @PathVariable("tid") Integer tid) {
        log.info("add(), user: {}, tid: {}", user, tid);

        Map<String, List> map = new HashMap<>();
        map.put("input", taskUnitInputService.list(tid));
        map.put("handle", taskUnitHandleService.list(tid));
        map.put("output", taskUnitOutputService.list(tid));

        return Result.success(map);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated(TaskUnitVO.Add.class) @RequestBody TaskUnitVO taskUnitVO) {
        log.info("add(), user: {}, taskUnitVO: {}", user, taskUnitVO);

        TaskUnit taskUnit = taskUnitService.add(taskUnitVO);

        return Result.success(taskUnit);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @Validated(TaskUnitVO.Delete.class) @RequestBody TaskUnitVO taskUnitVO) {
        log.info("deleteMultiple(), user: {}, taskUnitVO: {}", user, taskUnitVO);

        taskUnitService.delete(taskUnitVO);

        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result updateDis(User user, @Validated(TaskUnitVO.Update.class) @RequestBody TaskUnitVO taskUnitVO) {
        log.info("deleteMultiple(), user: {}, taskUnitVO: {}", user, taskUnitVO);

        taskUnitService.updateDis(taskUnitVO);

        return Result.success(null);
    }
}
