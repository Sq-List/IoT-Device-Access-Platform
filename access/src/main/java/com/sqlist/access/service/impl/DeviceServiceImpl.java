package com.sqlist.access.service.impl;

import com.sqlist.access.mapper.DeviceMapper;
import com.sqlist.access.service.DeviceService;
import com.sqlist.access.vo.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SqList
 * @date 2019/5/10 1:33
 * @description
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public boolean isExist(DeviceInfo deviceInfo) {
        return deviceMapper.existsWithPrimaryKey(deviceInfo);
    }
}
