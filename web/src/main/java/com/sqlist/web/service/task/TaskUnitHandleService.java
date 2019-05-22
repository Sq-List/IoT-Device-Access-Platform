package com.sqlist.web.service.task;

import com.sqlist.web.domain.TaskUnitHandle;
import com.sqlist.web.vo.TaskUnitHandleVO;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 1:14
 * @description
 **/
public interface TaskUnitHandleService {

    /**
     * 获取某个input
     * @param tuid
     * @return
     */
    TaskUnitHandle get(String tuid);

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitHandle> list(Integer tid);

    /**
     * 更新 detail
     * @param taskUnitHandleVO
     */
    void updateDetail(TaskUnitHandleVO taskUnitHandleVO);

    /**
     * 获取 tid 任务下 detail为 null 的 input
     * @param tid
     * @return
     */
    List<TaskUnitHandle> countDetailNull(Integer tid);

    /**
     * 统计 tid 任务下 handle 个数
     * @param tid
     * @return
     */
    Integer count(Integer tid);

    /**
     * 确认文件是否被handle使用
     * @param fid
     * @return true 为使用， false 为未使用
     */
    Boolean fileIsUsed(Integer fid);

    /**
     * 更新
     * @param taskUnitHandle
     */
    void update(TaskUnitHandle taskUnitHandle);
}
