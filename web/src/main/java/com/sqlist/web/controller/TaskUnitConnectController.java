package com.sqlist.web.controller;

import com.sqlist.web.domain.TaskUnitConnect;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskUnitConnectService;
import com.sqlist.web.vo.TaskUnitConnectVO;
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
 * @date 2019/4/23 18:15
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/taskUnitConnect")
public class TaskUnitConnectController {

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, Integer tid) {
        log.info("list(), user: {}, tid: {}", user, tid);

        List<TaskUnitConnect> taskUnitConnectList = taskUnitConnectService.list(tid);

        return Result.success(taskUnitConnectList);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated(TaskUnitConnectVO.Add.class) @RequestBody TaskUnitConnectVO taskUnitConnectVO) {
        log.info("add(), user: {}, taskUnitConnectVO: {}", user, taskUnitConnectVO);

        taskUnitConnectService.add(taskUnitConnectVO);

        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @Validated({TaskUnitConnectVO.Delete.class}) @RequestBody TaskUnitConnectVO taskUnitConnectVO) {
        taskUnitConnectService.delete(taskUnitConnectVO);
        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result update(User user,
                         @Validated(TaskUnitConnectVO.Update.class) @RequestBody TaskUnitConnectVO taskUnitConnectVO) {

        taskUnitConnectService.update(taskUnitConnectVO);

        return Result.success(null);
    }
}
