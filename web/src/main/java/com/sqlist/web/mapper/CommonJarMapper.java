package com.sqlist.web.mapper;

import com.sqlist.web.domain.CommonJar;
import com.sqlist.web.util.MyMapper;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
public interface CommonJarMapper extends MyMapper<CommonJar> {

    /**
     * 获取某一个 taskUnitType 所有支持的 type
     * @param taskUnitType
     * @return
     */
    List<String> selectAllTypeByTaskUnitType(String taskUnitType);
}