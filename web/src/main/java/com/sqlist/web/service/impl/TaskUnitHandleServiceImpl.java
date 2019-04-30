package com.sqlist.web.service.impl;

import com.sqlist.web.domain.TaskUnitHandle;
import com.sqlist.web.mapper.TaskUnitHandleMapper;
import com.sqlist.web.service.TaskUnitHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 2:43
 * @description
 **/
@Service
public class TaskUnitHandleServiceImpl implements TaskUnitHandleService {

    @Autowired
    private TaskUnitHandleMapper taskUnitHandleMapper;

    @Override
    public List<TaskUnitHandle> list(Integer tid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTid(tid);
        return taskUnitHandleMapper.select(taskUnitHandle);
    }
}
