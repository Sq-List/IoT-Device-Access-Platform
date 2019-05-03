package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.constants.TaskStatus;
import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.TaskUnitInput;
import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.TaskMapper;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.*;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author SqList
 * @date 2019/4/21 0:24
 * @description
 **/
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskUnitService taskUnitService;

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        Task task = new Task();
        task.setUid(user.getUid());

        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        List<Task> taskList = taskMapper.select(task);
        taskList.forEach((tmpTask) -> tmpTask.setStatus(TaskStatus.valueOf(tmpTask.getStatus()).getMsg()));

        map.put("total", ((Page)taskList).getTotal());
        map.put("list", taskList);

        return map;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void add(User user, TaskVO taskVO) {
        Task task = new Task();

        task.setName(taskVO.getName());
        task.setUid(user.getUid());

        if (taskMapper.selectCount(task) != 0) {
            throw new GlobalException(CodeMsg.TASK_NAME_REPEAT);
        }

        Date now = new Date();
        task.setStatus(TaskStatus.CODING.name());
        task.setCreateTime(now);
        task.setUpdateTime(now);
        taskMapper.insert(task);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteMultiple(User user, List<Integer> tidList) {
        taskMapper.deleteMultiple(user, tidList);

        // 删除相关的unit和connect
        taskUnitService.deleteMultiple(tidList);
        taskUnitConnectService.deleteMultiple(tidList);
    }

    @Override
    public Task detail(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        return taskMapper.selectByPrimaryKey(task);
    }

    @Override
    public void updateUpdateTime(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        task.setUpdateTime(new Date());
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void finish(Integer tid) {
        List<TaskUnit> unitList = new ArrayList<>(taskUnitInputService.countDetailNull(tid));
        unitList.addAll(taskUnitHandleService.countDetailNull(tid));
        unitList.addAll(taskUnitOutputService.countDetailNull(tid));

        if (unitList.size() != 0) {
            throw new GlobalException(CodeMsg.TASK_DETAIL_NULL, unitList);
        }

        int inputCount = taskUnitInputService.count(tid);
        int handleCount = taskUnitHandleService.count(tid);
        int outputCount = taskUnitOutputService.count(tid);

        if (inputCount != 1) {
            throw new GlobalException(CodeMsg.TASK_INPUT_ONE);
        }

        if (outputCount == 0) {
            throw new GlobalException(CodeMsg.TASK_OUTPUT_ZERO);
        }

        if (inputCount + handleCount + outputCount != taskUnitConnectService.count(tid) + 1) {
            throw new GlobalException(CodeMsg.TASK_CONNECT_LACK);
        }

        Task task = new Task();
        task.setTid(tid);
        task.setStatus(TaskStatus.UNUSE.name());
        taskMapper.updateByPrimaryKeySelective(task);
    }
}
