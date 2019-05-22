package com.sqlist.web.controller;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskSendSumService;
import com.sqlist.web.vo.BetweenTimeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/22 20:29
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/taskSendSum")
public class TaskSendSumController {

    @Autowired
    private TaskSendSumService taskSendSumService;

    @RequestMapping(value = "/1hour", method = RequestMethod.GET)
    public Result get1hourInfo(User user, BetweenTimeVO betweenTimeVO) {
        log.info("betweenTime: {}", betweenTimeVO);
        Task task = new Task();
        task.setUid(user.getUid());
        task.setTid(betweenTimeVO.getTid());
        return Result.success(taskSendSumService.getInfoBetweenTime(task, betweenTimeVO.getStartTime(), betweenTimeVO.getEndTime()));
    }

    @RequestMapping(value = "/7day", method = RequestMethod.GET)
    public Result get7DayInfo(User user, Integer tid) {
        Task task = new Task();
        task.setUid(user.getUid());
        task.setTid(tid);
        return Result.success(taskSendSumService.get7DayInfo(task));
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET)
    public Result getTotalInfo(User user, Integer tid) {
        Task task = new Task();
        task.setUid(user.getUid());
        task.setTid(tid);
        return Result.success(taskSendSumService.getTotalInfo(task));
    }
}
