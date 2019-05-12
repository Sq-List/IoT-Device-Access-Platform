package com.sqlist.admin.redis;

import com.alibaba.fastjson.JSON;
import com.sqlist.admin.redis.keyprefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author SqList
 * @date 2019/4/11 21:06
 * @description
 **/
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public <T> Integer keys(KeyPrefix keyPrefix) {
        Jedis jedis = jedisPool.getResource();
        try {
            String realKey = keyPrefix.getPrefix() + ":*";
            Integer nums = jedis.keys(realKey).size();
            return nums;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置值
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = jedisPool.getResource();
        try {
            String valueStr = beanToString(value);
            if (valueStr == null || valueStr.length() <= 0) {
                return false;
            }
            String realKey = keyPrefix.getPrefix() + ":" + key;
            int expireSeconds = keyPrefix.expireSeconds();
            if (expireSeconds <= 0) {
                jedis.set(realKey, valueStr);
            } else {
                jedis.setex(realKey, expireSeconds, valueStr);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * 获取值
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = jedisPool.getResource();
        try {
            String realKey = keyPrefix.getPrefix() + ":" + key;
            String valueStr = jedis.get(realKey);
            T t = stringToBean(valueStr, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Long del(KeyPrefix keyPrefix, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            String realKey = keyPrefix.getPrefix() + ":" + key;
            return jedis.del(realKey);
        } finally {
            returnToPool(jedis);
        }
    }
    /**
     * 值是否存在
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            //生成真正的key
            String realKey  = prefix.getPrefix() + ":" + key;
            return  jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 值增加
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 值减少
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T)str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }

        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String)value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
