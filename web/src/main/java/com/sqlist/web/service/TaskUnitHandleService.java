package com.sqlist.web.service;

import com.sqlist.web.domain.TaskUnitHandle;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 1:14
 * @description
 **/
public interface TaskUnitHandleService {

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitHandle> list(Integer tid);
}
