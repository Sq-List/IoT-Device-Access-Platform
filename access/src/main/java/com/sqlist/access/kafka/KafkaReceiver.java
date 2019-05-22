package com.sqlist.access.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.sqlist.access.domain.TaskSendSum;
import com.sqlist.access.service.TaskSendSumService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class KafkaReceiver {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TaskSendSumService taskSendSumService;

    @KafkaListener(topics = {"message-send-count"})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            String message = kafkaMessage.get();

            log.info("receive message: {}", message);
            try {
                TaskSendSum taskSendSum = JSON.parseObject(message, TaskSendSum.class);
                log.debug("taskSendSum: {}", taskSendSum);
                taskSendSumService.add(taskSendSum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
