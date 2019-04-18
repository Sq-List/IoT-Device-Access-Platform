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
        String paramToken = request.getParameter(SessionServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request, SessionServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            response.sendRedirect("/login");
            return false;
        }

        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (sessionService.getByToken(token) == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }


}
