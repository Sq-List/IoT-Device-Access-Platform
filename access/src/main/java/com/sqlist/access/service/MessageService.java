package com.sqlist.access.service;

/**
 * @author SqList
 * @date 2019/5/10 1:59
 * @description
 **/
public interface MessageService {

    /**
     * 分发
     * @param topic
     * @param message
     */
    void message(String topic, String message);

    /**
     * 将 mqtt 的消息转发到kafka上
     * @param productKey
     * @param message
     */
    void send(String productKey, String message);
}
