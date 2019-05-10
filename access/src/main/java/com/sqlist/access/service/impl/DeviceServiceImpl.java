package com.sqlist.access.service.impl;

import com.sqlist.access.constants.DeviceStatus;
import com.sqlist.access.domain.Device;
import com.sqlist.access.mapper.DeviceMapper;
import com.sqlist.access.service.DeviceService;
import com.sqlist.access.vo.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        return deviceMapper.countByProductAndDevice(deviceInfo) == 1;
    }

    @Override
    public void login(String deviceKey) {
        Device device = new Device();
        device.setDeviceKey(deviceKey);
        device = deviceMapper.selectOne(device);

        Date now = new Date();
        if (device.getActiveTime() == null) {
            device.setActiveTime(now);
        }
        device.setStatus(DeviceStatus.ONLINE.name());
        device.setLastTime(now);

        deviceMapper.updateByPrimaryKeySelective(device);
    }

    @Override
    public void loginOut(String deviceKey) {
        Device device = new Device();
        device.setDeviceKey(deviceKey);
        device = deviceMapper.selectOne(device);

        device.setStatus(DeviceStatus.OFFLINE.name());
        deviceMapper.updateByPrimaryKeySelective(device);
    }
}
