package com.sqlist.web.service;

import com.sqlist.web.domain.User;
import com.sqlist.web.vo.LoginVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author SqList
 * @date 2019/4/12 0:40
 * @description
 **/
public interface SessionService {

    User getByUsername(String username);

    User getByToken(String token);

    /**
     * 登录接口
     * @param loginVO
     * @return
     */
    boolean login(LoginVO loginVO);

    boolean logout();
}
