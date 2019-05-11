package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 17:46
 * @description
 **/
@Controller
@RequestMapping("/page")
public class OverviewPageController {

    @RequestMapping(value = {"/overview", ""}, method = RequestMethod.GET)
    public String overviewView() {
        return "overview";
    }
}
