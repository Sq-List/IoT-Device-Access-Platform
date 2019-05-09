package com.sqlist.access.service.impl;

import com.sqlist.access.domain.Product;
import com.sqlist.access.kafka.KafkaSender;
import com.sqlist.access.service.MessageService;
import com.sqlist.access.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SqList
 * @date 2019/5/10 2:03
 * @description
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private ProductService productService;

    @Override
    public void send(String mqttTopic, String message) {
        String[] topicSpilt = mqttTopic.split("/");
        Product product = productService.get(topicSpilt[1]);

        kafkaSender.send(product.getTopic(), message);
    }
}
