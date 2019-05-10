package com.sqlist.access.service.impl;

import com.alibaba.fastjson.JSON;
import com.sqlist.access.exception.MqttGlobalException;
import com.sqlist.access.result.CodeMsg;
import com.sqlist.access.result.Result;
import com.sqlist.access.service.DeviceService;
import com.sqlist.access.service.SessionService;
import com.sqlist.access.util.mqtt.MqttClient;
import com.sqlist.access.vo.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SqList
 * @date 2019/5/10 1:29
 * @description
 **/
@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    public static final String REGEX = "/session/(\\w+?)/(\\w+?)/thing/(\\w+?)";

    public static final String LOGIN = "login";
    public static final String LOGIN_OUT = "login_out";

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private DeviceService deviceService;

    @Async
    @Override
    public void session(String topic, String message) throws MqttException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(topic);
        if (matcher.matches()) {
            String thing = matcher.group(3);
            log.info("thing: {}", thing);

            switch (thing) {
                case LOGIN:
                    DeviceInfo deviceInfo = JSON.parseObject(message, DeviceInfo.class);
                    login(deviceInfo);
                    break;

                case LOGIN_OUT:
                    String deviceKey = matcher.group(2);
                    loginOut(deviceKey);
                    break;

                default:
                    log.info("不存在对应主题");
                    break;
            }
        }
    }

    @Override
    public void login(DeviceInfo deviceInfo) throws MqttException {
        String topic = "/client/session/" + deviceInfo.getProductKey() + "/" + deviceInfo.getDeviceKey() + "/thing/login";

        if (!deviceService.isExist(deviceInfo)) {
            mqttClient.publish(topic, JSON.toJSONString(Result.fail(CodeMsg.LOGIN_FAIL)));
        }

        deviceService.login(deviceInfo.getDeviceKey());
        mqttClient.publish(topic, JSON.toJSONString(Result.success(null)));
    }

    @Override
    public void loginOut(String deviceKey) {
        deviceService.loginOut(deviceKey);
    }
}
