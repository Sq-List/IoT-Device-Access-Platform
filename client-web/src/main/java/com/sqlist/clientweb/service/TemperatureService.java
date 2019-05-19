package com.sqlist.clientweb.service;

import com.sqlist.clientweb.domain.Temperature;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/19 21:14
 * @description
 **/
public interface TemperatureService {

    /**
     * 添加
     * @param temperature
     */
    void add(Temperature temperature);

    /**
     * 获取
     * @return
     */
    List<Temperature> list();
}
