package com.sqlist.mqtt;

import com.alibaba.fastjson.JSON;
import com.sqlist.result.Result;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author SqList
 * @date 2019/5/8 20:50
 * @description
 **/
public class PushCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("断开连接");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + s);
        System.out.println("接收消息Qos:" + mqttMessage.getQos());
        System.out.println("接收消息内容:" + new String(mqttMessage.getPayload()));
        Result result = JSON.parseObject(mqttMessage.getPayload(), Result.class);
        if (result.getCode() == 0) {
            new Thread(() -> Client.start()).start();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // publish后会执行到这里
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
