package com.sqlist.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/18 18:52
 * @description
 **/
@Controller
@RequestMapping("/page")
public class IndexPageController {

    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
