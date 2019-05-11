package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 17:50
 * @description
 **/
@Controller
@RequestMapping("/page/user")
public class UserPageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "user/list";
    }

    @RequestMapping(value = "/{uid}/detail", method = RequestMethod.GET)
    public String detailView(@PathVariable("uid") Integer uid) {
        return "user/detail";
    }
}
