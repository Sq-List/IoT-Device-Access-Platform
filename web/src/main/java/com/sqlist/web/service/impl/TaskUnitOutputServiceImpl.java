package com.sqlist.web.service.impl;

import com.sqlist.web.domain.TaskUnitOutput;
import com.sqlist.web.mapper.TaskUnitOutputMapper;
import com.sqlist.web.service.TaskUnitOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 2:45
 * @description
 **/
@Service
public class TaskUnitOutputServiceImpl implements TaskUnitOutputService {

    @Autowired
    private TaskUnitOutputMapper taskUnitOutputMapper;

    @Override
    public List<TaskUnitOutput> list(Integer tid) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTid(tid);
        return taskUnitOutputMapper.select(taskUnitOutput);
    }
}
