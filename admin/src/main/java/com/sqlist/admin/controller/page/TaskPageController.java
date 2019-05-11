package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 19:29
 * @description
 **/
@Controller
@RequestMapping("/page/task")
public class TaskPageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "task/list";
    }
}
