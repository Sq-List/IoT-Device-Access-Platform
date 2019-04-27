package com.sqlist.web.service;

import com.sqlist.web.domain.Device;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.DeviceVO;
import com.sqlist.web.vo.PageVO;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/26 1:28
 * @description
 **/
public interface DeviceService {

    /**
     * 获取设备列表
     * @param deviceVO
     * @param pageVO
     * @return
     */
    Map<String, Object> list(DeviceVO deviceVO, PageVO pageVO);

    /**
     * 添加 设备
     * @param user
     * @param deviceVO
     */
    void add(User user, DeviceVO deviceVO);

    /**
     * 删除 设备
     * @param user
     * @param didList
     */
    void deleteMultiple(User user, List<Integer> didList);

    /**
     * 更新多个设备的pid
     * @param device
     * @param didList
     */
    void updatePidMultipleByDid(Device device, List<Integer> didList);

    /**
     * 更新多个设备的pid
     * @param device
     * @param pidList
     */
    void updatePidMultipleByPid(Device device, List<Integer> pidList);

    /**
     * 获取设备详细信息
     * @param device
     * @return
     */
    Device detail(Device device);
}
