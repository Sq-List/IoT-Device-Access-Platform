package com.sqlist.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/19 11:06
 * @description
 **/
@Controller
@RequestMapping("/page/task")
public class TaskPageController {

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public String list() {
        return "task/list";
    }

    @RequestMapping(value = "/{tid}/edit", method = RequestMethod.GET)
    public String create(@PathVariable("tid") int tid) {
        return "task/edit";
    }
}
