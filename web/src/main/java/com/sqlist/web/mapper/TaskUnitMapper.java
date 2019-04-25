package com.sqlist.web.mapper;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
public interface TaskUnitMapper extends MyMapper<TaskUnit> {

    /**
     * 根据 tid  删除多个 unit
     * @param tidList
     */
    void deleteMultiple(@Param("tidList") List<Integer> tidList);
}