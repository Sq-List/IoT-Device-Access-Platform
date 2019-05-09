package com.sqlist.access.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author SqList
 * @date 2019/5/8 17:03
 * @description
 **/
@Slf4j
@Component
public class KafkaSender {

    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
