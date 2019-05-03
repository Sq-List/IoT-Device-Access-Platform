package com.sqlist.web.service.impl;

import com.sqlist.web.domain.TaskUnitOutput;
import com.sqlist.web.mapper.TaskUnitOutputMapper;
import com.sqlist.web.service.TaskService;
import com.sqlist.web.service.TaskUnitOutputService;
import com.sqlist.web.vo.TaskUnitOutputVO;
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

    @Autowired
    private TaskService taskService;

    @Override
    public TaskUnitOutput get(String tuid) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTuid(tuid);
        return taskUnitOutputMapper.selectByPrimaryKey(taskUnitOutput);
    }

    @Override
    public List<TaskUnitOutput> list(Integer tid) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTid(tid);
        return taskUnitOutputMapper.select(taskUnitOutput);
    }

    @Override
    public void updateDetail(TaskUnitOutputVO taskUnitOutputVO) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTuid(taskUnitOutputVO.getTuid());
        taskUnitOutput.setType(taskUnitOutputVO.getType());
        taskUnitOutput.setIp(taskUnitOutputVO.getIp());
        taskUnitOutput.setPort(taskUnitOutputVO.getPort());
        taskUnitOutput.setUrl(taskUnitOutputVO.getUrl());

        taskUnitOutputMapper.updateByPrimaryKeySelective(taskUnitOutput);

        // 更新task的updateTime
        taskService.updateUpdateTime(taskUnitOutputVO.getTid());
    }

    @Override
    public List<TaskUnitOutput> countDetailNull(Integer tid) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTid(tid);
        taskUnitOutput.setType("");
        taskUnitOutput.setIp("");
        taskUnitOutput.setPort("");
        return taskUnitOutputMapper.select(taskUnitOutput);
    }

    @Override
    public Integer count(Integer tid) {
        TaskUnitOutput taskUnitOutput = new TaskUnitOutput();
        taskUnitOutput.setTid(tid);
        return taskUnitOutputMapper.selectCount(taskUnitOutput);
    }
}
