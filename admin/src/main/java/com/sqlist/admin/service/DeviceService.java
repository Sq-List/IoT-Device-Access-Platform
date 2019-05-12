package com.sqlist.admin.service;

import com.sqlist.admin.domain.Device;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.vo.DeviceVO;
import com.sqlist.admin.vo.PageVO;

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
     * 删除 设备
     * @param didList
     */
    void deleteMultiple(List<Integer> didList);

    /**
     * 删除某产品下的所有设备
     * @param pidList
     */
    void deleteMultipleByPid(List<Integer> pidList);

    /**
     * 获取设备详细信息
     * @param device
     * @return
     */
    Device detail(Device device);

    /**
     * 获取所有设备数
     * @return
     */
    Integer count();

    /**
     * 获取在线设备
     * @return
     */
    Integer countOnline();
}
