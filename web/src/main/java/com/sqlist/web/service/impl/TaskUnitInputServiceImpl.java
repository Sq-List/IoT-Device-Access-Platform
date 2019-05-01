package com.sqlist.web.service.impl;

import com.sqlist.web.domain.TaskUnitInput;
import com.sqlist.web.mapper.TaskUnitInputMapper;
import com.sqlist.web.service.TaskUnitInputService;
import com.sqlist.web.vo.TaskUnitInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 2:37
 * @description
 **/
@Service
public class TaskUnitInputServiceImpl implements TaskUnitInputService {

    @Autowired
    private TaskUnitInputMapper taskUnitInputMapper;

    @Override
    public TaskUnitInput get(String tuid) {
        TaskUnitInput taskUnitInput = new TaskUnitInput();
        taskUnitInput.setTuid(tuid);
        return taskUnitInputMapper.selectByPrimaryKey(taskUnitInput);
    }

    @Override
    public List<TaskUnitInput> list(Integer tid) {
        TaskUnitInput taskUnitInput = new TaskUnitInput();
        taskUnitInput.setTid(tid);
        return taskUnitInputMapper.select(taskUnitInput);
    }

    @Override
    public void updateDetail(TaskUnitInputVO taskUnitInputVO) {
        TaskUnitInput taskUnitInput = new TaskUnitInput();
        taskUnitInput.setTuid(taskUnitInputVO.getTuid());
        taskUnitInput.setPid(taskUnitInputVO.getPid());

        taskUnitInputMapper.updateByPrimaryKeySelective(taskUnitInput);
    }
}
