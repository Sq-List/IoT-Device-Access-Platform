package com.sqlist.util;

import com.sqlist.OperatingData;

/**
 * @author SqList
 * @date 2019/5/6 16:01
 * @description
 **/
public class Factory {

    public static OperatingData getComponent(String componentPath) {
        try {
            Class<?> componentClass = Class.forName(componentPath);

            return (OperatingData) componentClass.newInstance();
        } catch (Exception ex) {
            return null;
        }
    }
}
