package com.sqlist.web.constants;

/**
 * @author SqList
 * @date 2019/5/10 14:38
 * @description
 **/
public enum DeviceStatus {
    // 未激活
    UNACTIVE("未激活"),
    // 在线
    ONLINE("在线"),
    // 离线
    OFFLINE("离线");

    private String msg;

    DeviceStatus(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
