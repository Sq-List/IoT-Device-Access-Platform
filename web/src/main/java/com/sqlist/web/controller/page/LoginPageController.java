package com.sqlist.web.controller.page;

import com.sqlist.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
@Controller
@RequestMapping(value = "/")
public class LoginPageController {

    @RequestMapping(value = {"/login" , ""}, method = RequestMethod.GET)
    public String loginView(User user) {

        if (user != null) {
            return "redirect:/page/overview";
        }

        return "login";
    }

}
