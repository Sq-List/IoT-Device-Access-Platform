package com.sqlist.access.mapper;

import com.sqlist.access.domain.Device;
import com.sqlist.access.util.MyMapper;
import com.sqlist.access.vo.DeviceInfo;

public interface DeviceMapper extends MyMapper<Device> {

    Integer countByProductAndDevice(DeviceInfo info);
}