package com.sqlist.web.service;

import com.sqlist.web.domain.TaskUnitOutput;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 1:14
 * @description
 **/
public interface TaskUnitOutputService {

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitOutput> list(Integer tid);
}
