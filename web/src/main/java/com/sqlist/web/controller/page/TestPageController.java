package com.sqlist.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author SqList
 * @date 2019/4/18 15:52
 * @description
 **/
@Controller
@RequestMapping("/page")
public class TestPageController {

    @RequestMapping("/test")
    public String test() {
        return "test/test";
    }
}
