package com.sqlist.web.controller;

import com.sqlist.web.result.Result;
import com.sqlist.web.service.SessionService;
import com.sqlist.web.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
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
