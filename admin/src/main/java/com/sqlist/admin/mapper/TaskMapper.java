package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.Task;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.util.MyMapper;
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
     * @param tidList
     */
    void deleteMultiple(@Param("tidList") List<Integer> tidList);
}