package com.sqlist.mqtt;

import com.alibaba.fastjson.JSON;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author SqList
 * @date 2019/5/9 16:02
 * @description
 * qos: 最多一次 0、最少一次 1、只有一次 2
 **/
public class Client {

    public static final String LOGIN_PUBLISH = "/session/%s/%s/thing/login";
    public static final String LOGIN_OUT_PUBLISH = "/session/%s/%s/thing/login_out";
    public static final String LOGIN_SUBSCRIBE = "/client/session/%s/%s/thing/login";
    public static final String MESSAGE_PUBLISH = "/message/%s/%s/thing/send";

    private static final String HOST = "tcp://192.168.56.101:1883";

    private static ArrayBlockingQueue<String> messageList = new ArrayBlockingQueue<String>(100);

    private static MqttClient mqttClient;
    private static String sendTopic;

    private Client() {

    }

    public static ArrayBlockingQueue<String> getMessageList() {
        return messageList;
    }

    public static void init(DeviceInfo deviceInfo) throws MqttException {

        sendTopic = String.format(MESSAGE_PUBLISH, deviceInfo.getProductKey(), deviceInfo.getDeviceKey());
        String loginOutPulish = String.format(LOGIN_OUT_PUBLISH, deviceInfo.getProductKey(), deviceInfo.getDeviceKey());
        String loginPublish = String.format(LOGIN_PUBLISH, deviceInfo.getProductKey(), deviceInfo.getDeviceKey());
        String loginSubscribe = String.format(LOGIN_SUBSCRIBE, deviceInfo.getProductKey(), deviceInfo.getDeviceKey());

        mqttClient = new MqttClient(HOST, deviceInfo.getDeviceKey(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        //options.setUserName(deviceInfo.getUsername());
        // 设置连接的密码
        //options.setPassword(deviceInfo.getPassword().toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        options.setWill(loginOutPulish, "".getBytes(), 0, false);
        // 设置回调
        mqttClient.setCallback(new PushCallback());

        mqttClient.connect(options);


        mqttClient.subscribe(loginSubscribe, 2);

        MqttMessage loginMessage = new MqttMessage(JSON.toJSONString(deviceInfo).getBytes());
        loginMessage.setQos(2);
        mqttClient.publish(loginPublish, loginMessage);
    }

    public static void start() {
        try {
            Random random = new Random();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (true) {
//                String message = messageList.take();
                String temperature = random.nextInt(100) + "";
                String message = "{\"temperature\": " + temperature + ", \"time\": \"" + sdf.format(new Date()) + "\"}";
                MqttMessage mqttMessage = new MqttMessage(message.getBytes());
                try {
                    System.out.println("send message" + message);
                    mqttClient.publish(sendTopic, mqttMessage);
                } catch (MqttException e) {
                    e.printStackTrace();
                }

                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
