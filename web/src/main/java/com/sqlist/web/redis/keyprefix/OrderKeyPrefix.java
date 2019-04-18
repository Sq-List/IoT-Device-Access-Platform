package com.sqlist.web.redis.keyprefix;


/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
public class OrderKeyPrefix extends BaseKeyPrefix {
    public OrderKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
