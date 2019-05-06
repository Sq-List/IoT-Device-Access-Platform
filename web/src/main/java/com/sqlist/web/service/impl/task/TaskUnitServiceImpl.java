package com.sqlist.web.service.impl.task;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.TaskUnitConnect;
import com.sqlist.web.mapper.TaskUnitMapper;
import com.sqlist.web.service.task.TaskService;
import com.sqlist.web.service.task.TaskUnitConnectService;
import com.sqlist.web.service.task.TaskUnitService;
import com.sqlist.web.util.UUIDUtil;
import com.sqlist.web.vo.TaskUnitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @Autowired
    private TaskService taskService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public TaskUnit add(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(taskUnitVO.getType().toLowerCase() + "-" + UUIDUtil.uuid());
        taskUnit.setLeftDis(taskUnitVO.getLeft());
        taskUnit.setTopDis(taskUnitVO.getTop());
        taskUnit.setCreateTime(new Date());
        taskUnit.setTid(taskUnitVO.getTid());

        taskUnitMapper.insert(taskUnitVO.getType().toLowerCase(), taskUnit);

        return taskUnit;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(taskUnitVO.getTuid());
        taskUnit.setTid(taskUnitVO.getTid());

        taskUnitMapper.delete(taskUnitVO.getType().toLowerCase(), taskUnit);

        // 删除相关联的连接
        TaskUnitConnect taskUnitConnect = new TaskUnitConnect();
        taskUnitConnect.setTid(taskUnitVO.getTid());
        taskUnitConnect.setSourceTuid(taskUnitVO.getTuid());
        taskUnitConnectService.taskUnitDelete(taskUnitConnect);

        // 更新task的updateTime
        taskService.updateUpdateTime(taskUnitVO.getTid());
    }

    @Override
    public void deleteMultiple(List<Integer> deleteTidList) {
        taskUnitMapper.deleteMultiple("input", deleteTidList);
        taskUnitMapper.deleteMultiple("handle", deleteTidList);
        taskUnitMapper.deleteMultiple("output", deleteTidList);
    }

    @Override
    public void updateDis(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(taskUnitVO.getTuid());
        taskUnit.setLeftDis(taskUnitVO.getLeft());
        taskUnit.setTopDis(taskUnitVO.getTop());
        taskUnit.setTid(taskUnitVO.getTid());

        taskUnitMapper.updateDis(taskUnitVO.getType().toLowerCase(), taskUnit);

        // 更新task的updateTime
        taskService.updateUpdateTime(taskUnitVO.getTid());
    }
}
