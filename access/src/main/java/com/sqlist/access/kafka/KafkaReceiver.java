package com.sqlist.access.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author SqList
 * @date 2019/5/12 15:31
 * @description
 **/
@Component
public class KafkaReceiver {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = {"message-send-sum"})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            String message = kafkaMessage.get();


        }
    }
}
