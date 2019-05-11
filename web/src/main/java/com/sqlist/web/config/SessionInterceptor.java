package com.sqlist.web.config;

import com.sqlist.web.service.SessionService;
import com.sqlist.web.service.impl.SessionServiceImpl;
import com.sqlist.web.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SqList
 * @date 2019/4/18 15:19
 * @description 进入页面时，如果未登录则跳转到登录页面
 **/
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取header和cookie里面的token值
        String paramToken = request.getParameter(SessionServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request, SessionServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            // 都不存在，说明没有登录
            response.sendRedirect("/login");
            return false;
        }

        // 取其中一个有值的
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (sessionService.getByToken(token) == null) {
            // 缓存中不存在该token的key，说明登录失效
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }


}
