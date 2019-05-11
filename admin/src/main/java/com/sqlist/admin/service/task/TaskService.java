package com.sqlist.admin.service.task;

import com.sqlist.admin.domain.Task;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.vo.PageVO;
import com.sqlist.admin.vo.TaskVO;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/21 0:11
 * @description
 **/
public interface TaskService {

    /**
     * 获取任务列表
     * @param user
     * @param pageVO
     * @return
     */
    Map<String, Object> list(User user, PageVO pageVO);

    /**
     * 删除任务
     * @param deleteList
     */
    void deleteMultiple(List<Integer> deleteList);

    /**
     * 获取task信息
     * @param tid
     * @return
     */
    Task detail(Integer tid);

    /**
     * 获取处于 启动中 的任务
     * @return
     */
    List<Task> getStartingTask();
}
