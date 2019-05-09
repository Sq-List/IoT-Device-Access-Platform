package com.sqlist.access.service;

import com.sqlist.access.vo.DeviceInfo;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author SqList
 * @date 2019/5/10 0:48
 * @description
 **/
public interface SessionService {

    void login(DeviceInfo deviceInfo) throws MqttException;
}
