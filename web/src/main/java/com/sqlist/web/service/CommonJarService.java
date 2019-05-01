package com.sqlist.web.service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/2 2:21
 * @description
 **/
public interface CommonJarService {

    /**
     * 获取某一个 taskUnitType 所有支持的 type
     * @param taskUnitType
     * @return
     */
    List<String> getType(String taskUnitType);
}
