package com.sqlist.admin.service.impl.task;

import com.sqlist.admin.mapper.TaskUnitMapper;
import com.sqlist.admin.service.task.TaskUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/22 15:52
 * @description
 **/
@Slf4j
@Service
public class TaskUnitServiceImpl implements TaskUnitService {

    @Autowired
    private TaskUnitMapper taskUnitMapper;

    @Override
    public void deleteMultiple(List<Integer> deleteTidList) {
        taskUnitMapper.deleteMultiple("input", deleteTidList);
        taskUnitMapper.deleteMultiple("handle", deleteTidList);
        taskUnitMapper.deleteMultiple("output", deleteTidList);
    }
}
