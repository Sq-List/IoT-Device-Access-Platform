package com.sqlist.admin.mapper;


import com.sqlist.admin.domain.File;
import com.sqlist.admin.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/28 1:19
 * @description
 **/
public interface FileMapper extends MyMapper<File> {

    /**
     * select 文件 和对应的用户
     * @param file
     * @return
     */
    List<Map<String, String>> selectWithUser(File file);
}