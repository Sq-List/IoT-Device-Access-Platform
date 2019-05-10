package com.sqlist.access.service.impl;

import com.sqlist.access.domain.Product;
import com.sqlist.access.kafka.KafkaSender;
import com.sqlist.access.service.MessageService;
import com.sqlist.access.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SqList
 * @date 2019/5/10 2:03
 * @description
 **/
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    public static final String REGEX = "/message/(\\w+)?/(\\w+)?/thing/(\\w+)?";

    public static final String SEND = "send";

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private ProductService productService;

    @Async
    @Override
    public void message(String topic, String message) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(topic);
        if (matcher.matches()) {
            String thing = matcher.group(3);
            log.info("thing: {}", thing);

            switch (thing) {
                case SEND:
                    String productKey = matcher.group(1);
                    send(productKey, message);
                    break;

                default:
                    log.info("不存在对应主题");
                    break;
            }
        }
    }

    @Override
    public void send(String productKey, String message) {
        Product product = productService.get(productKey);

        log.info("发送到 topic：{}, message:{}", product.getTopic(), message);
        kafkaSender.send(product.getTopic(), message);
    }
}
