package com.sqlist.web.controller;

import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/4/19 0:18
 * @description
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result user(User user) {
        user.setPassword(null);
        return Result.success(user);
    }
}
