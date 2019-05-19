package com.sqlist.clientweb.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/19 21:23
 * @description
 **/
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
