package com.sqlist.access.service.impl;

import com.alibaba.fastjson.JSON;
import com.sqlist.access.exception.MqttGlobalException;
import com.sqlist.access.result.CodeMsg;
import com.sqlist.access.result.Result;
import com.sqlist.access.service.DeviceService;
import com.sqlist.access.service.SessionService;
import com.sqlist.access.util.mqtt.MqttClient;
import com.sqlist.access.vo.DeviceInfo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author SqList
 * @date 2019/5/10 1:29
 * @description
 **/
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private DeviceService deviceService;

    @Async
    @Override
    public void login(DeviceInfo deviceInfo) throws MqttException {
        String topic = "/session/" + deviceInfo.getProductKey() + "/" + deviceInfo.getDeviceKey() + "/thing/login_reply";

        if (!deviceService.isExist(deviceInfo)) {
            throw new MqttGlobalException(topic, CodeMsg.LOGIN_FAIL);
        }

        mqttClient.publish(topic, JSON.toJSONString(Result.success(null)));
    }
}
