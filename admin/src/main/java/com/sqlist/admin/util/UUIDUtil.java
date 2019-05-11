package com.sqlist.admin.util;

import java.util.UUID;

/**
 * @author SqList
 * @date 2019/4/12 1:02
 * @description
 **/
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String uuid(String seed) {
        return UUID.fromString(seed).toString().replace("-", "");
    }
}
