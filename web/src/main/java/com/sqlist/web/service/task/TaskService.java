package com.sqlist.web.service.task;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;
import com.sqlist.web.vo.search.TaskSearchVO;

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
     * @param taskSearchVO
     * @return
     */
    Map<String, Object> list(User user, TaskSearchVO taskSearchVO);

    /**
     * 添加任务
     * @param user
     * @param taskVO
     */
    void add(User user, TaskVO taskVO);

    /**
     * 删除任务
     * @param user
     * @param deleteList
     */
    void deleteMultiple(User user, List<Integer> deleteList);

    /**
     * 获取task信息
     * @param tid
     * @return
     */
    Task detail(Integer tid);

    /**
     * 更新 updateTime
     * @param tid
     */
    void updateUpdateTime(Integer tid);

    /**
     * 更新
     * @param task
     */
    void update(Task task);

    /**
     * 获取处于 启动中 的任务
     * @return
     */
    List<Task> getStartingTask();

    /**
     * 开发完成
     * @param tid
     */
    void finish(Integer tid);

    /**
     * 启动 任务
     * @param tid
     */
    void start(Integer tid);

    /**
     * 停止 任务
     * @param tid
     */
    void stop(Integer tid);

    /**
     * 统计某用户的所有任务数
     * @param user
     * @return
     */
    Integer count(User user);
}
