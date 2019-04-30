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
public interface TaskUnitMapper {

    /**
     * 根据 tid  删除多个 unit
     * @param type
     * @param tidList
     */
    void deleteMultiple(@Param("type") String type, @Param("tidList") List<Integer> tidList);

    /**
     * 插入
     * @param type
     * @param taskUnit
     */
    void insert(@Param("type") String type, @Param("taskUnit") TaskUnit taskUnit);

    /**
     * 更新 （left和top）
     * @param type
     * @param taskUnit
     */
    void updateDis(@Param("type") String type, @Param("taskUnit") TaskUnit taskUnit);

    /**
     * 删除
     * @param type
     * @param taskUnit
     */
    void delete(@Param("type") String type, @Param("taskUnit") TaskUnit taskUnit);
}