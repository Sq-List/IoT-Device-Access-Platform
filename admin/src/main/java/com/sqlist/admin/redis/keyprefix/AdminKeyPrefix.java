package com.sqlist.admin.redis.keyprefix;

import com.sqlist.admin.service.impl.SessionServiceImpl;

/**
 * @author SqList
 * @date 2019/5/11 16:19
 * @description
 **/
public class AdminKeyPrefix extends BaseKeyPrefix {

    public static final int TOKEN_EXPIRE = 60 * 60 * 2;

    public AdminKeyPrefix(String prefix) {
        super(prefix);
    }

    public AdminKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AdminKeyPrefix TOKEN = new AdminKeyPrefix(TOKEN_EXPIRE, SessionServiceImpl.COOKIE_NAME_TOKEN);
}
