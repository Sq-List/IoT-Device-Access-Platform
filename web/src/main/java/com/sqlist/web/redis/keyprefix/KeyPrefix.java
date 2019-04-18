package com.sqlist.web.redis.keyprefix;

/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
