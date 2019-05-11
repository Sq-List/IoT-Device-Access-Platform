package com.sqlist.admin.service.impl.task;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.admin.constants.TaskStatus;
import com.sqlist.admin.domain.Task;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.exception.GlobalException;
import com.sqlist.admin.mapper.TaskMapper;
import com.sqlist.admin.result.CodeMsg;
import com.sqlist.admin.service.task.TaskService;
import com.sqlist.admin.service.task.TaskUnitConnectService;
import com.sqlist.admin.service.task.TaskUnitService;
import com.sqlist.admin.vo.PageVO;
import com.sqlist.admin.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author SqList
 * @date 2019/4/21 0:24
 * @description
 **/
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskUnitService taskUnitService;

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        Task task = new Task();
        if (user != null) {
            task.setUid(user.getUid());
        }

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
    public void deleteMultiple(List<Integer> tidList) {
        taskMapper.deleteMultiple(tidList);

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
    public List<Task> getStartingTask() {
        Task task = new Task();
        task.setStatus(TaskStatus.STARTING.name());
        return taskMapper.select(task);
    }
}
