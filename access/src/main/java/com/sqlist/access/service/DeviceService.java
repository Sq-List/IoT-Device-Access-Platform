package com.sqlist.access.service;

import com.sqlist.access.vo.DeviceInfo;

/**
 * @author SqList
 * @date 2019/5/10 1:31
 * @description
 **/
public interface DeviceService {

    /**
     * 判断是否存在符合 deviceInfo 的device
     * @param deviceInfo
     * @return
     */
    boolean isExist(DeviceInfo deviceInfo);

    /**
     * 设备上线
     * @param deviceKey
     */
    void login(String deviceKey);

    /**
     * 设备下线
     * @param deviceKey
     */
    void loginOut(String deviceKey);
}
