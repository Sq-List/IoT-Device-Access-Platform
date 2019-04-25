package com.sqlist.web.mapper;

import com.sqlist.web.domain.TaskUnitConnect;
import com.sqlist.web.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
public interface TaskUnitConnectMapper extends MyMapper<TaskUnitConnect> {

    /**
     * 根据 source, target, tid 删除
     * @param taskUnitConnect
     */
    void taskUnitDelete(TaskUnitConnect taskUnitConnect);

    /**
     * 根据 tid 删除 connect
     * @param tidList
     */
    void deleteMultiple(@Param("tidList") List<Integer> tidList);
}