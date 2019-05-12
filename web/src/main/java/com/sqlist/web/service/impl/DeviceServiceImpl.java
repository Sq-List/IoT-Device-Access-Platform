package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.constants.DeviceStatus;
import com.sqlist.web.domain.Device;
import com.sqlist.web.domain.User;
import com.sqlist.web.mapper.DeviceMapper;
import com.sqlist.web.service.DeviceService;
import com.sqlist.web.util.UUIDUtil;
import com.sqlist.web.vo.DeviceResponseVO;
import com.sqlist.web.vo.DeviceVO;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.search.DeviceSearchVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public Map<String, Object> list(DeviceVO deviceVO, DeviceSearchVO deviceSearchVO) {
        Device device = new Device();
        device.setName(deviceSearchVO.getName());
        device.setDeviceKey(deviceSearchVO.getDeviceKey());
        device.setStatus(deviceSearchVO.getStatus());
        device.setUid(deviceVO.getUid());
        device.setPid(deviceVO.getPid());

        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(deviceSearchVO.getPage(), deviceSearchVO.getLimit());
        List<DeviceResponseVO> deviceResponseVOList = deviceMapper.selectWithProduct(device);
        deviceResponseVOList.forEach((tmpDevice) -> tmpDevice.setStatus(DeviceStatus.valueOf(tmpDevice.getStatus()).getMsg()));

        map.put("total", ((Page)deviceResponseVOList).getTotal());
        map.put("list", deviceResponseVOList);

        return map;
    }

    @Override
    public void add(User user, DeviceVO deviceVO) {
        Device device = new Device();
        device.setUid(user.getUid());
        device.setName(deviceVO.getName());
        if (StringUtils.isEmpty(deviceVO.getDeviceKey())) {
            device.setDeviceKey(UUIDUtil.uuid());
        } else {
            device.setDeviceKey(deviceVO.getDeviceKey());
        }
        device.setDeviceSecret(UUIDUtil.uuid());
        device.setPid(deviceVO.getPid());
        device.setStatus(DeviceStatus.UNACTIVE.name());
        device.setCreateTime(new Date());

        deviceMapper.insert(device);
    }

    @Override
    public void deleteMultiple(User user, List<Integer> didList) {
        deviceMapper.deleteMultiple(user, didList);
    }

    @Override
    public void deleteMultipleByPid(Device device, List<Integer> pidList) {
        deviceMapper.deleteMultipleByPid(device, pidList);
    }

//    @Transactional(rollbackFor = RuntimeException.class)
//    @Override
//    public void updatePidMultipleByDid(Device device, List<Integer> didList) {
//        deviceMapper.updatePidMultipleByDid(device, didList);
//    }
//
//    @Override
//    public void updatePidMultipleByPid(Device device, List<Integer> pidList) {
//        deviceMapper.updatePidMultipleByPid(device, pidList);
//    }

    @Override
    public Device detail(Device device) {
        device = deviceMapper.selectByPrimaryKey(device);
        device.setStatus(DeviceStatus.valueOf(device.getStatus()).getMsg());
        return device;
    }
}
