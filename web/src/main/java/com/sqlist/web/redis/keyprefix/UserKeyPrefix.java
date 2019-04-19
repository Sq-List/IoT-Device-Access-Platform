package com.sqlist.web.redis.keyprefix;


import com.sqlist.web.service.impl.SessionServiceImpl;

/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
public class UserKeyPrefix extends BaseKeyPrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public UserKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKeyPrefix TOKEN = new UserKeyPrefix(TOKEN_EXPIRE, SessionServiceImpl.COOKIE_NAME_TOKEN);
}
