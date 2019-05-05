package com.sqlist.web.service.impl;

import com.sqlist.web.domain.CommonJar;
import com.sqlist.web.mapper.CommonJarMapper;
import com.sqlist.web.service.CommonJarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/2 2:22
 * @description
 **/
@Slf4j
@Service
public class CommonJarServiceImpl implements CommonJarService {

    @Autowired
    private CommonJarMapper commonJarMapper;

    @Override
    public List<String> getType(String taskUnitType) {
        return commonJarMapper.selectAllTypeByTaskUnitType(taskUnitType);
    }

    @Override
    public CommonJar get(String unitType, String type) {
        CommonJar commonJar = new CommonJar();
        commonJar.setTaskUnitType(unitType);
        commonJar.setType(type);
        return commonJarMapper.select(commonJar).get(0);
    }
}
