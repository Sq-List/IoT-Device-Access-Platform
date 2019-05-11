package com.sqlist.admin.service.impl;

import com.sqlist.admin.domain.Admin;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.exception.GlobalException;
import com.sqlist.admin.mapper.AdminMapper;
import com.sqlist.admin.mapper.UserMapper;
import com.sqlist.admin.redis.RedisUtil;
import com.sqlist.admin.redis.keyprefix.AdminKeyPrefix;
import com.sqlist.admin.redis.keyprefix.UserKeyPrefix;
import com.sqlist.admin.result.CodeMsg;
import com.sqlist.admin.service.SessionService;
import com.sqlist.admin.util.CookieUtil;
import com.sqlist.admin.util.UUIDUtil;
import com.sqlist.admin.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SqList
 * @date 2019/4/12 0:41
 * @description
 **/
@Service
public class SessionServiceImpl implements SessionService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Override
    public Admin getByUsername(String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        return adminMapper.selectOne(admin);
    }

    @Override
    public Admin getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        Admin admin = redisUtil.get(AdminKeyPrefix.TOKEN, token, Admin.class);
        // 延长有效期
        if (admin != null) {
            redisUtil.set(AdminKeyPrefix.TOKEN, token, admin);
            addTokenCookie(token);
        }
        return admin;
    }

    @Override
    public void login(LoginVO loginVO) {
        Admin admin = getByUsername(loginVO.getUsername());
        if (admin == null) {
            throw new GlobalException(CodeMsg.LOGIN_ERROR);
        }

        if (!loginVO.getPassword().equals(admin.getPassword())) {
            throw new GlobalException(CodeMsg.LOGIN_ERROR);
        }

        String token = UUIDUtil.uuid();
        redisUtil.set(AdminKeyPrefix.TOKEN, token, admin);
        addTokenCookie(token);
    }

    @Override
    public void logout() {

        String paramToken = request.getParameter(SessionServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request, SessionServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return ;
        }

        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (redisUtil.exists(AdminKeyPrefix.TOKEN, token)) {
            redisUtil.del(AdminKeyPrefix.TOKEN, token);
        }

        deleteTokenCookie();
    }

    private void addTokenCookie(String token) {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(AdminKeyPrefix.TOKEN.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void deleteTokenCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
