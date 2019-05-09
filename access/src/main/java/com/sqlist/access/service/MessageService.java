package com.sqlist.access.service;

/**
 * @author SqList
 * @date 2019/5/10 1:59
 * @description
 **/
public interface MessageService {

    /**
     * 将 mqtt 的消息转发到kafka上
     * @param mqttTopic
     * @param message
     */
    void send(String mqttTopic, String message);
}
