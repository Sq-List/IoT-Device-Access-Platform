package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.constants.TaskStatus;
import com.sqlist.web.domain.Task;
import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.TaskMapper;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.TaskService;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/21 0:24
 * @description
 **/
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

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
}
