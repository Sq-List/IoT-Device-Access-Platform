package com.sqlist.admin.service.task;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:04
 * @description
 **/
public interface TaskUnitService {

    /**
     * 删除与 tid 关联的taskUnit
     *
     * @param deleteTidList
     */
    void deleteMultiple(List<Integer> deleteTidList);
}
