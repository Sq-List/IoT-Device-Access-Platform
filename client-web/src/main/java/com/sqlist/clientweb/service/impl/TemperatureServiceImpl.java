package com.sqlist.clientweb.service.impl;

import com.sqlist.clientweb.domain.Temperature;
import com.sqlist.clientweb.mapper.TemperatureMapper;
import com.sqlist.clientweb.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SqList
 * @date 2019/5/19 23:48
 * @description
 **/
@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Override
    public void add(Temperature temperature) {
        temperatureMapper.insert(temperature);
    }

    @Override
    public List<Temperature> list() {
        List<Temperature> temperatureList = temperatureMapper.selectNewest50();
        List<Temperature> temperatures = temperatureList.stream().sorted(Comparator.comparing(Temperature::getTid)).collect(Collectors.toList());
        return temperatures;
    }
}
