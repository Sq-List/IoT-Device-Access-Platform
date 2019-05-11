package com.sqlist.admin.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 18:11
 * @description
 **/
@Controller
@RequestMapping("/page/product")
public class ProductPageController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listView() {
        return "product/list";
    }

    @RequestMapping(value = "/{pid}/detail", method = RequestMethod.GET)
    public String detailView() {
        return "product/detail";
    }
}
