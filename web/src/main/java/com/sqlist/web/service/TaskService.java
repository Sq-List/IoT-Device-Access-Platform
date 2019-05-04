package com.sqlist.web.service;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;

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
}
