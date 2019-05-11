package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.TaskUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
public interface TaskUnitMapper {

    /**
     * 根据 tid  删除多个 unit
     * @param type
     * @param tidList
     */
    void deleteMultiple(@Param("type") String type, @Param("tidList") List<Integer> tidList);
}