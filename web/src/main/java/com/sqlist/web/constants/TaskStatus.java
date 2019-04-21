package com.sqlist.web.constants;

/**
 * @author SqList
 * @date 2019/4/21 1:45
 * @description
 **/
public enum TaskStatus {
    // 开发中
    CODING("开发中"),
    // 未使用
    UNUSE("未使用"),
    // 使用中
    USING("使用中");

    private String msg;

    TaskStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
