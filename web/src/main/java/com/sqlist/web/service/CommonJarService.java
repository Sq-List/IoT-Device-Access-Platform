package com.sqlist.web.service;

import com.sqlist.web.domain.CommonJar;

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

    /**
     * 根据 unitType type 获取 commonJar
     * @param unitType
     * @param type
     * @return
     */
    CommonJar get(String unitType, String type);
}
