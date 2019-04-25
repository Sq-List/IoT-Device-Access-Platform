package com.sqlist.web.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/25 10:31
 * @description
 **/
@Slf4j
@Controller
@RequestMapping("/page/product")
public class ProductPageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "product/list";
    }

    @RequestMapping(value = "/{pid}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("pid") Integer pid) {
        return "product/detail";
    }
}
