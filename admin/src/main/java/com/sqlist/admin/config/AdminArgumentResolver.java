package com.sqlist.admin.config;

import com.sqlist.admin.domain.Admin;
import com.sqlist.admin.service.SessionService;
import com.sqlist.admin.service.impl.SessionServiceImpl;
import com.sqlist.admin.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SqList
 * @date 2019/4/12 0:34
 * @description controller 中的方法参数若有 Admin 类型的参数，则通过该类利用 token 转换成 Admin 类，注入进去
 **/
@Component
public class AdminArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SessionService sessionService;

    /**
     * 该方法返回true时，才会执行 resolveArgument()
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == Admin.class;
    }

    /**
     * 转换过程
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        String paramToken = request.getParameter(SessionServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request, SessionServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return sessionService.getByToken(token);
    }
}
