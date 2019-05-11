package com.sqlist.admin.controller;

import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.SessionService;
import com.sqlist.admin.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/11 17:15
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * 登录
     * @param loginVO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result login(@Validated @RequestBody LoginVO loginVO) {
        log.info("loginVO: {}", loginVO);

        sessionService.login(loginVO);

        return Result.success(true);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result logout() {
        sessionService.logout();

        return Result.success(true);
    }
}
