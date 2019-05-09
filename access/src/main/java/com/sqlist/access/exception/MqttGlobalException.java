package com.sqlist.access.exception;

import com.sqlist.access.result.CodeMsg;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio
 **/
public class MqttGlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    private String topic;

    public MqttGlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public MqttGlobalException(String topic, CodeMsg codeMsg) {
        this(codeMsg);
        this.topic = topic;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public String getTopic() {
        return topic;
    }
}
