package com.sqlist.admin.controller;

import com.alibaba.fastjson.JSON;
import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.task.TaskService;
import com.sqlist.admin.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/12 1:11
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
     * @param pageVO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        Map<String, Object> map = taskService.list(null, pageVO);
        return Result.success(map);
    }

    /**
     * 删除 多个task
     * @param deleteTidList
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(@RequestBody List<Integer> deleteTidList) {
        log.info("deleteMultiple(), deleteMultiple: {}", JSON.toJSONString(deleteTidList));

        taskService.deleteMultiple(deleteTidList);

        return Result.success(null);
    }

    @RequestMapping(value = "/{tid}", method = RequestMethod.GET)
    public Result detail(@PathVariable("tid") Integer tid) {
        return Result.success(taskService.detail(tid));
    }
}
