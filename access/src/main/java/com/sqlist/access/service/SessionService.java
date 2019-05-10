package com.sqlist.access.service;

import com.sqlist.access.vo.DeviceInfo;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author SqList
 * @date 2019/5/10 0:48
 * @description
 **/
public interface SessionService {

    /**
     * 分发
     * @param topic
     * @param message
     * @throws MqttException
     */
    void session(String topic, String message) throws MqttException;

    /**
     * 设备上线
     * @param deviceInfo
     * @throws MqttException
     */
    void login(DeviceInfo deviceInfo) throws MqttException;

    /**
     * 设备下线
     * @param deviceKey
     */
    void loginOut(String deviceKey);
}
