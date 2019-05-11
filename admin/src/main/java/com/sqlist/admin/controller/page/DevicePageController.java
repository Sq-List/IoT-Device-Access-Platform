package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 18:14
 * @description
 **/
@Controller
@RequestMapping("/page/device")
public class DevicePageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "device/list";
    }

    @RequestMapping(value = "/{did}/detail", method = RequestMethod.GET)
    public String detailView() {
        return "device/detail";
    }
}
