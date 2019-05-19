package com.sqlist.clientweb.mapper;

import com.sqlist.clientweb.domain.Temperature;
import com.sqlist.clientweb.util.MyMapper;

import java.util.List;

public interface TemperatureMapper extends MyMapper<Temperature> {

    List<Temperature> selectNewest50();
}