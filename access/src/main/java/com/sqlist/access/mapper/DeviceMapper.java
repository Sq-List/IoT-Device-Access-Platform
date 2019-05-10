package com.sqlist.access.mapper;

import com.sqlist.access.domain.Device;
import com.sqlist.access.util.MyMapper;
import com.sqlist.access.vo.DeviceInfo;

public interface DeviceMapper extends MyMapper<Device> {

    /**
     * 统计 符合 deviceInfo 的device
     * @param info
     * @return
     */
    Integer countByProductAndDevice(DeviceInfo info);
}