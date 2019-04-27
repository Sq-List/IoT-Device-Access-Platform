package com.sqlist.web.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/27 19:13
 * @description
 **/
@Slf4j
@Controller
@RequestMapping("/page/file")
public class FilePageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "file/list";
    }
}
