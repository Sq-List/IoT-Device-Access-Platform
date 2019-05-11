package com.sqlist.admin.controller.page;

import com.sqlist.admin.domain.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/5/11 16:35
 * @description
 **/
@Controller
@RequestMapping("/")
public class LoginPageController {

    @RequestMapping(value = {"", "/login"}, method = RequestMethod.GET)
    public String loginView(Admin admin) {

        if (admin != null) {
            return "redirect:/page/overview";
        }

        return "login";
    }
}
