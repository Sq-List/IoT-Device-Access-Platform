package com.sqlist.admin.service.impl.task;

import com.sqlist.admin.mapper.TaskUnitConnectMapper;
import com.sqlist.admin.service.task.TaskUnitConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/23 21:40
 * @description
 **/
@Slf4j
@Service
public class TaskUnitConnectServiceImpl implements TaskUnitConnectService {

    @Autowired
    private TaskUnitConnectMapper taskUnitConnectMapper;

    @Override
    public void deleteMultiple(List<Integer> deleteTidList) {
        taskUnitConnectMapper.deleteMultiple(deleteTidList);
    }
}
