package com.sqlist.admin.controller;

import com.sqlist.admin.domain.Task;
import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.TaskSendSumService;
import com.sqlist.admin.vo.BetweenTimeVO;
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
    public Result get1hourInfo(BetweenTimeVO betweenTimeVO) {
        log.info("betweenTime: {}", betweenTimeVO);
        Task task = new Task();
        task.setTid(betweenTimeVO.getTid());
        return Result.success(taskSendSumService.getInfoBetweenTime(task, betweenTimeVO.getStartTime(), betweenTimeVO.getEndTime()));
    }

    @RequestMapping(value = "/7day", method = RequestMethod.GET)
    public Result get7DayInfo(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        return Result.success(taskSendSumService.get7DayInfo(task));
    }

    @RequestMapping(value = "/total", method = RequestMethod.GET)
    public Result getTotalInfo(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        return Result.success(taskSendSumService.getTotalInfo(task));
    }
}
