package com.sqlist.web.service;

import com.sqlist.web.domain.TaskUnitInput;
import com.sqlist.web.vo.TaskUnitInputVO;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
public interface TaskUnitInputService {

    /**
     * 获取某个input
     * @param tuid
     * @return
     */
    TaskUnitInput get(String tuid);

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitInput> list(Integer tid);

    /**
     * 更新 detail
     * @param taskUnitInputVO
     */
    void updateDetail(TaskUnitInputVO taskUnitInputVO);
}
