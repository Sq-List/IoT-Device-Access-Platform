package com.sqlist.web.service;

import com.sqlist.web.domain.TaskUnitInput;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
public interface TaskUnitInputService {

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitInput> list(Integer tid);
}
