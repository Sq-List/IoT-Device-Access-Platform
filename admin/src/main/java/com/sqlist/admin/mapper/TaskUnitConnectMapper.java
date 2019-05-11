package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.TaskUnitConnect;
import com.sqlist.admin.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
public interface TaskUnitConnectMapper extends MyMapper<TaskUnitConnect> {

    /**
     * 根据 tid 删除 connect
     * @param tidList
     */
    void deleteMultiple(@Param("tidList") List<Integer> tidList);
}