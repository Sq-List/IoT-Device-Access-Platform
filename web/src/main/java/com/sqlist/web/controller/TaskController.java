package com.sqlist.web.controller;

import com.alibaba.fastjson.JSON;
import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.TaskService;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 获取task列表
     * @param user
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, @Validated PageVO pageVO) {
        Map<String, Object> map = taskService.list(user, pageVO);
        return Result.success(map);
    }

    /**
     * 添加task
     * @param user
     * @param taskVO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated @RequestBody TaskVO taskVO) {
        log.info("add(), user: {}, taskVO: {}", user, taskVO);
        taskService.add(user, taskVO);
        return Result.success(null);
    }

    /**
     * 删除 多个task
     * @param user
     * @param deleteTidList
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @RequestBody List<Integer> deleteTidList) {
        log.info("deleteMultiple(), user: {} deleteMultiple: {}", user, JSON.toJSONString(deleteTidList));

        taskService.deleteMultiple(user, deleteTidList);

        return Result.success(null);
    }
}
