package com.sqlist.web.service;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;

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
}
