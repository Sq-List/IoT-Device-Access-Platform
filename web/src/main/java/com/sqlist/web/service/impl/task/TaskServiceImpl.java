package com.sqlist.web.service.impl.task;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.constants.TaskStatus;
import com.sqlist.web.domain.*;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.TaskMapper;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.flink.TaskFlinkService;
import com.sqlist.web.service.task.*;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    private TaskUnitInputService taskUnitInputService;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @Lazy
    @Autowired
    private TaskFlinkService taskFlinkService;

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

    @Override
    public void update(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public List<Task> getStartingTask() {
        Task task = new Task();
        task.setStatus(TaskStatus.STARTING.name());
        return taskMapper.select(task);
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

        // input 有且仅有一个
        if (inputCount != 1) {
            throw new GlobalException(CodeMsg.TASK_INPUT_ONE);
        }

        // output 至少要有一个
        if (outputCount == 0) {
            throw new GlobalException(CodeMsg.TASK_OUTPUT_ZERO);
        }

        // 连接线 要比 unit 少一个  这样所有的unit才都是已连接
        if (inputCount + handleCount + outputCount != taskUnitConnectService.count(tid) + 1) {
            throw new GlobalException(CodeMsg.TASK_CONNECT_LACK);
        }

        Task task = new Task();
        task.setTid(tid);
        task.setStatus(TaskStatus.UNUSE.name());
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public void start(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        task.setStatus(TaskStatus.STARTING.name());

        log.info("before start.");
        taskFlinkService.start(tid);
        log.info("after start.");

        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public void stop(Integer tid) {
        Task task = new Task();
        task.setTid(tid);
        task.setStatus(TaskStatus.UNUSE.name());

        log.info("before stop.");
        taskFlinkService.stop(tid);
        log.info("after stop.");

        taskMapper.updateByPrimaryKeySelective(task);
    }
}
