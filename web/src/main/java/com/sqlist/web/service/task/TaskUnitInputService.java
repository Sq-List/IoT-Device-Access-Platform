package com.sqlist.web.service.task;

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

    /**
     * 获取 tid 任务下 detail为 null 的 input
     * @param tid
     * @return
     */
    List<TaskUnitInput> countDetailNull(Integer tid);

    /**
     * 统计 tid 任务下 input 个数
     * @param tid
     * @return
     */
    Integer count(Integer tid);

    /**
     * 确认产品是否被input使用
     * @param pid
     * @return true 为使用， false 为未使用
     */
    Boolean productIsUsed(Integer pid);
}
