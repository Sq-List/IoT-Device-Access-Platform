package com.sqlist.admin.service.task;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/23 21:38
 * @description
 **/
public interface TaskUnitConnectService {

    /**
     * 删除与 tid 关联的taskUnit
     * @param deleteTidList
     */
    void deleteMultiple(List<Integer> deleteTidList);
}
