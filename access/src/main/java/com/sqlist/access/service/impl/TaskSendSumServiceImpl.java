package com.sqlist.access.service.impl;

import com.sqlist.access.domain.TaskSendSum;
import com.sqlist.access.mapper.TaskSendSumMapper;
import com.sqlist.access.service.TaskSendSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SqList
 * @date 2019/5/13 15:20
 * @description
 **/
@Service
public class TaskSendSumServiceImpl implements TaskSendSumService {

    @Autowired
    private TaskSendSumMapper taskSendSumMapper;

    @Override
    public void add(TaskSendSum taskSendSum) {
        taskSendSumMapper.insert(taskSendSum);
    }
}
