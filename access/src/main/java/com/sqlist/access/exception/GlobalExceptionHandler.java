package com.sqlist.access.exception;

import com.alibaba.fastjson.JSON;
import com.sqlist.access.result.Result;
import com.sqlist.access.util.mqtt.MqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio 全局异常处理器
 **/
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Autowired
    private MqttClient mqttClient;

    @ExceptionHandler(value = MqttGlobalException.class)
    public void handleGlobalException(MqttGlobalException e) throws MqttException {
        log.error("raised MqttGlobalException: {}", e.getCodeMsg().toString());
        mqttClient.publish(e.getTopic(), JSON.toJSONString(Result.fail(e.getCodeMsg())));
    }

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(Exception e) {
        e.printStackTrace();
    }
}
