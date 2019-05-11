package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.Device;
import com.sqlist.admin.util.MyMapper;
import com.sqlist.admin.vo.response.DeviceResponseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/25 10:31
 * @description
 **/
public interface DeviceMapper extends MyMapper<Device> {

    /**
     * 获取 设备及所属产品
     * @param device
     * @return
     */
    List<DeviceResponseVO> selectWithProduct(Device device);

    /**
     * 删除某产品下的所有设备
     * @param pidList
     */
    void deleteMultipleByPid(@Param("pidList") List<Integer> pidList);

    /**
     * 根据did删除多个设备
     * @param didList
     */
    void deleteMultiple(@Param("didList") List<Integer> didList);
}