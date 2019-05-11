package com.sqlist.admin.service;

import com.sqlist.admin.domain.Admin;
import com.sqlist.admin.vo.LoginVO;

/**
 * @author SqList
 * @date 2019/4/12 0:40
 * @description
 **/
public interface SessionService {

    /**
     * 根据用户名获取Admin
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 根据token获取admin
     * @param token
     * @return
     */
    Admin getByToken(String token);

    /**
     * 登录
     * @param loginVO
     * @return
     */
    void login(LoginVO loginVO);

    /**
     * 登出
     * @return
     */
    void logout();
}
