package com.sqlist.web.controller;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskUnitServce;
import com.sqlist.web.vo.TaskUnitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private TaskUnitServce taskUnitServce;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, Integer tid) {
        log.info("add(), user: {}, tid: {}", user, tid);

        List<TaskUnit> taskUnitList = taskUnitServce.list(tid);

        return Result.success(taskUnitList);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated(TaskUnitVO.Add.class) @RequestBody TaskUnitVO taskUnitVO) {
        log.info("add(), user: {}, taskUnitVO: {}", user, taskUnitVO);

        TaskUnit taskUnit = taskUnitServce.add(taskUnitVO);

        return Result.success(taskUnit);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @Validated(TaskUnitVO.Delete.class) @RequestBody TaskUnitVO taskUnitVO) {
        log.info("delete(), user: {}, taskUnitVO: {}", user, taskUnitVO);

        taskUnitServce.delete(taskUnitVO);

        return Result.success(null);
    }
}
