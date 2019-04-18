package com.sqlist.web.redis.keyprefix;

/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
public abstract class BaseKeyPrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BaseKeyPrefix(String prefix) {
        this(0, prefix);
    }

    public BaseKeyPrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {    // 0为永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName() + ":" + prefix;
    }
}
