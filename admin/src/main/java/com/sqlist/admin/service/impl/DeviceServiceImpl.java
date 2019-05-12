package com.sqlist.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.admin.constants.DeviceStatus;
import com.sqlist.admin.domain.Device;
import com.sqlist.admin.mapper.DeviceMapper;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.vo.DeviceVO;
import com.sqlist.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/26 1:34
 * @description
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Map<String, Object> list(DeviceVO deviceVO, PageVO pageVO) {
        Device device = new Device();
        device.setUid(deviceVO.getUid());
        device.setPid(deviceVO.getPid());

        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        List<Map<String, String>> deviceResponseVOList = deviceMapper.selectWithProductAndUser(device);
        deviceResponseVOList.forEach((tmpDevice) -> tmpDevice.put("status", DeviceStatus.valueOf(tmpDevice.get("status")).getMsg()));

        map.put("total", ((Page)deviceResponseVOList).getTotal());
        map.put("list", deviceResponseVOList);

        return map;
    }

    @Override
    public void deleteMultiple(List<Integer> didList) {
        deviceMapper.deleteMultiple(didList);
    }

    @Override
    public void deleteMultipleByPid(List<Integer> pidList) {
        deviceMapper.deleteMultipleByPid(pidList);
    }

    @Override
    public Device detail(Device device) {
        device = deviceMapper.selectByPrimaryKey(device);
        device.setStatus(DeviceStatus.valueOf(device.getStatus()).getMsg());
        return device;
    }

    @Override
    public Integer count() {
        return deviceMapper.selectCount(new Device());
    }

    @Override
    public Integer countOnline() {
        Device device = new Device();
        device.setStatus(DeviceStatus.ONLINE.name());
        return deviceMapper.selectCount(device);
    }
}
