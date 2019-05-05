package com.sqlist.web.service.task;

import com.sqlist.web.domain.TaskUnitOutput;
import com.sqlist.web.vo.TaskUnitOutputVO;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 1:14
 * @description
 **/
public interface TaskUnitOutputService {

    /**
     * 获取某个input
     * @param tuid
     * @return
     */
    TaskUnitOutput get(String tuid);

    /**
     * 获取
     * @param tid
     * @return
     */
    List<TaskUnitOutput> list(Integer tid);

    /**
     * 更新 detail
     * @param taskUnitOutputVO
     */
    void updateDetail(TaskUnitOutputVO taskUnitOutputVO);

    /**
     * 获取 tid 任务下 detail为 null 的 input
     * @param tid
     * @return
     */
    List<TaskUnitOutput> countDetailNull(Integer tid);

    /**
     * 统计 tid 任务下 output 个数
     * @param tid
     * @return
     */
    Integer count(Integer tid);

    /**
     * 更新
     * @param taskUnitOutput
     */
    void update(TaskUnitOutput taskUnitOutput);
}
