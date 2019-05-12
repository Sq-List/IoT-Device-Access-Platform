package com.sqlist.admin.config.mybatis;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/13 0:59
 * @description
 **/
public class CustomWrapper extends MapWrapper {


    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }


    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            //CaseFormat是引用的 guava库,里面有转换驼峰的,免得自己重复造轮子,pom添加
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        }
        return name;
    }
}
