package com.sqlist.web.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/26 21:47
 * @description
 **/
@Slf4j
@Controller
@RequestMapping("/page/device")
public class DevicePageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "device/list";
    }

    @RequestMapping(value = "/{did}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("did") Integer did) {
        return "device/detail";
    }
}
