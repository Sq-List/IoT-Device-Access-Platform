package com.sqlist.web.service;

import com.sqlist.web.vo.DeviceVO;
import com.sqlist.web.vo.PageVO;

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
     * @return
     */
    Map<String, Object> list(DeviceVO deviceVO, PageVO pageVO);
}
