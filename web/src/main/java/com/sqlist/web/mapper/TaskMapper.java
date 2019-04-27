package com.sqlist.web.mapper;

import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
public interface TaskMapper extends MyMapper<Task> {

    /**
     * 根据tid删除多个任务
     * @param user
     * @param tidList
     */
    void deleteMultiple(@Param("user") User user, @Param("tidList") List<Integer> tidList);
}