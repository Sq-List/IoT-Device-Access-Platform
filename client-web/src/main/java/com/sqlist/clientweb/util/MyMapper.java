package com.sqlist.clientweb.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author SqList
 * @date 2019/4/9 16:54
 * @description
 **/

public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>{

}