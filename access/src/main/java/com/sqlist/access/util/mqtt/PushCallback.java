package com.sqlist.access.util.mqtt;

import com.alibaba.fastjson.JSON;
import com.sqlist.access.service.MessageService;
import com.sqlist.access.service.SessionService;
import com.sqlist.access.vo.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SqList
 * @date 2019/5/10 0:05
 * @description
 **/
@Slf4j
@Component
public class PushCallback implements MqttCallback {

    public static final String SESSION = "/session";
    public static final String MESSAGE = "/message";

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MessageService messageService;

    /**
     * 断开连接时调用
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.error("断开连接： {}", throwable.getMessage());
        throwable.printStackTrace();
    }

    /**
     * subscribe后得到的消息时调用
     * @param topic
     * @param mqttMessage
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        String message = new String(mqttMessage.getPayload());
        log.info("收到 topic: {}, message: {}", topic, message);

        try {
            if (topic.startsWith(SESSION)) {
                log.info("session 消息");
                sessionService.session(topic, message);
            } else if (topic.startsWith(MESSAGE)) {
                log.info("message 消息");
                messageService.message(topic, message);
            } else {

            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * publish后调用
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("已发送");
    }
}
