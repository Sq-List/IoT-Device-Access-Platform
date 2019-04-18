package com.sqlist.web.service.impl;

import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.UserMapper;
import com.sqlist.web.redis.RedisUtil;
import com.sqlist.web.redis.keyprefix.UserKeyPrefix;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.SessionService;
import com.sqlist.web.util.CookieUtil;
import com.sqlist.web.util.UUIDUtil;
import com.sqlist.web.vo.LoginVO;
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
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Override
    public User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }

    @Override
    public User getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        User user = redisUtil.get(UserKeyPrefix.TOKEN, token, User.class);
        // 延长有效期
        if (user != null) {
            addTokenCookie(token, user);
        }
        return user;
    }

    @Override
    public boolean login(LoginVO loginVO) {
        if (loginVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        User user = getByUsername(loginVO.getUsername());
        if (user == null) {
            throw new GlobalException(CodeMsg.LOGIN_ERROR);
        }

        // 加密
//        String dbPass = user.getPassword();
//        String dbSalt = user.getSalt();
//        String inputPass = MD5Util.formPassToDBPass(loginVO.getPassword(), dbSalt);

        if (!loginVO.getPassword().equals(user.getPassword())) {
            throw new GlobalException(CodeMsg.LOGIN_ERROR);
        }

        // 将登陆状态添加到redis中
        // 并生成token返回到客户端
        String uuid = UUIDUtil.uuid();
        redisUtil.set(UserKeyPrefix.TOKEN, uuid, user);
        addTokenCookie(uuid, user);

        return true;
    }

    @Override
    public boolean logout() {

        String paramToken = request.getParameter(SessionServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request, SessionServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return true;
        }

        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (redisUtil.exists(UserKeyPrefix.TOKEN, token)) {
            redisUtil.del(UserKeyPrefix.TOKEN, token);
        }

        deleteTokenCookie();

        return true;
    }

    private void addTokenCookie(String token, User user) {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKeyPrefix.TOKEN.expireSeconds());
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
