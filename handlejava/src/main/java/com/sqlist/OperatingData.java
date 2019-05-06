package com.sqlist;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;

/**
 * @author SqList
 * @date 2019/5/6 16:02
 * @description
 **/
public interface OperatingData {

    DataStream<String> operate(DataStreamSource<String> dataStreamSource);
}
