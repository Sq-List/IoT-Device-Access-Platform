package com.sqlist.web.service.task;

import com.sqlist.web.domain.TaskUnitConnect;
import com.sqlist.web.vo.TaskUnitConnectVO;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/23 21:38
 * @description
 **/
public interface TaskUnitConnectService {

    /**
     * 获取 连接
     * @param tid
     * @return
     */
    List<TaskUnitConnect> list(Integer tid);

    /**
     * 添加 连接
     * @param taskUnitConnectVO
     */
    void add(TaskUnitConnectVO taskUnitConnectVO);

    /**
     * 删除 连接
     * @param taskUnitConnectVO
     */
    void delete(TaskUnitConnectVO taskUnitConnectVO);

    /**
     * taskUnit 删除，需要删除与之相关联的 connect
     * @param taskUnitConnect
     */
    void taskUnitDelete(TaskUnitConnect taskUnitConnect);

    /**
     * 删除与 tid 关联的taskUnit
     * @param deleteTidList
     */
    void deleteMultiple(List<Integer> deleteTidList);

    /**
     * 更新 连接
     * @param taskUnitConnectVO
     */
    void update(TaskUnitConnectVO taskUnitConnectVO);

    /**
     * 统计 tid 任务的 connect
     * @param tid
     * @return
     */
    Integer count(Integer tid);
}
