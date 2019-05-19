package com.sqlist.clientweb.kafka;

import com.alibaba.fastjson.JSON;
import com.sqlist.clientweb.domain.TemperatureAlarm;
import com.sqlist.clientweb.service.TemperatureAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author SqList
 * @date 2019/5/19 20:24
 * @description
 **/
@Slf4j
@Component
public class AlarmReceiver {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TemperatureAlarmService temperatureAlarmService;

    @KafkaListener(topics = {"warehouse-temperature-alarm"})
    public void listen(ConsumerRecord<String, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            String message = kafkaMessage.get();

            log.info("receive message: {}", message);
            TemperatureAlarm temperatureAlarm = JSON.parseObject(message, TemperatureAlarm.class);
            temperatureAlarmService.add(temperatureAlarm);
        }
    }
}
