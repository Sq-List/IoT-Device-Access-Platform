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
    UNUSE("开发完成"),
    // 启动中
    STARTING("启动中"),
    // 使用中
    RUNNING("运行中"),
    // 停止中
    STOPING("停止中");

    private String msg;

    TaskStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
