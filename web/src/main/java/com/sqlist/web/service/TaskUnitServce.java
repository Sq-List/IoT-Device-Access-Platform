package com.sqlist.web.service;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.vo.TaskUnitVO;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:04
 * @description
 **/
public interface TaskUnitServce {

    /**
     * 获取 taskUnit
     * @param tid
     * @return
     */
    List<TaskUnit> list(Integer tid);

    /**
     * 添加 taskUnit
     * @param taskUnitVO
     * @return
     */
    TaskUnit add(TaskUnitVO taskUnitVO);

    /**
     * 删除 taskUnit
     * @param taskUnitVO
     */
    void delete(TaskUnitVO taskUnitVO);

    /**
     * 删除与 tid 关联的taskUnit
     * @param deleteTidList
     */
    void deleteMultiple(List<Integer> deleteTidList);

    /**
     * 更新 taskUnit
     * @param taskUnitVO
     */
    void update(TaskUnitVO taskUnitVO);
}
