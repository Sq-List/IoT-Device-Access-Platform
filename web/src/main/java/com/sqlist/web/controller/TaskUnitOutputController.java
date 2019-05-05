package com.sqlist.web.controller;

import com.sqlist.web.result.Result;
import com.sqlist.web.service.task.TaskUnitOutputService;
import com.sqlist.web.vo.TaskUnitOutputVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{tuid}", method = RequestMethod.POST)
    public Result updateDetail(@PathVariable("tuid") String tuid, @Validated @RequestBody TaskUnitOutputVO taskUnitOutputVO) {
        taskUnitOutputVO.setTuid(tuid);
        taskUnitOutputService.updateDetail(taskUnitOutputVO);
        return Result.success(null);
    }
}
