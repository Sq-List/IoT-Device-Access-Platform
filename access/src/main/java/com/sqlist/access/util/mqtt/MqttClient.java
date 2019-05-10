package com.sqlist.access.util.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author SqList
 * @date 2019/5/9 23:23
 * @description
 **/
@Component
public class MqttClient {

    @Value("tcp://${config.mqtt.ip}")
    private String host;

    @Value("${config.mqtt.username}")
    private String username;

    @Value("${config.mqtt.password}")
    private String password;

    @Value("${config.mqtt.clientId}")
    private String clientId;

    @Value("${config.mqtt.topic.subscribe.login}")
    private String subscribeLogin;

    @Value("${config.mqtt.topic.subscribe.message}")
    private String subscribeMessage;

    @Autowired
    private MqttCallback callback;

    private org.eclipse.paho.client.mqttv3.MqttClient mqttClient;

    private MqttClient() {

    }

    @PostConstruct
    private void init() throws MqttException {
        mqttClient = new org.eclipse.paho.client.mqttv3.MqttClient(host, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        // 设置回调
        mqttClient.setCallback(callback);
        mqttClient.connect(options);

        String[] topics = {subscribeLogin, subscribeMessage};
        int[] qos = {2, 2};
        mqttClient.subscribe(topics, qos);
    }

    @PreDestroy
    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(2);
        mqttClient.publish(topic, mqttMessage);
    }
}
