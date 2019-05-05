package com.sqlist.web.constants;

/**
 * @author SqList
 * @date 2019/5/6 0:28
 * @description
 **/
public enum TaskJobStatus {
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

    TaskJobStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
