package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 18:15
 * @description
 **/
@Controller
@RequestMapping("/page")
public class FilePageController {

    @RequestMapping(value = "/file/list", method = RequestMethod.GET)
    public String fileListView() {
        return "file/list";
    }

    @RequestMapping(value = "/commonJar/list", method = RequestMethod.GET)
    public String commonJarListView() {
        return "commonJar/list";
    }
}
