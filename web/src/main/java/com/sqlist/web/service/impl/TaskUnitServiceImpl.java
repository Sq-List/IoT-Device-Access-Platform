package com.sqlist.web.service.impl;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.TaskUnitConnect;
import com.sqlist.web.mapper.TaskUnitMapper;
import com.sqlist.web.service.TaskUnitConnectService;
import com.sqlist.web.service.TaskUnitServce;
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
public class TaskUnitServiceImpl implements TaskUnitServce {

    public static final String TASK_UNIT_UID_PREFIX = "tu-";

    @Autowired
    private TaskUnitMapper taskUnitMapper;

    @Autowired
    private TaskUnitConnectService taskUnitConnectService;

    @Override
    public List<TaskUnit> list(Integer tid) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTid(tid);

        List<TaskUnit> taskUnitList = taskUnitMapper.select(taskUnit);

        return taskUnitList;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public TaskUnit add(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(TASK_UNIT_UID_PREFIX + UUIDUtil.uuid());
        taskUnit.setType(taskUnitVO.getType());
        taskUnit.setLeftDis(taskUnitVO.getLeft());
        taskUnit.setTopDis(taskUnitVO.getTop());
        taskUnit.setTid(taskUnitVO.getTid());
        taskUnit.setCreateTime(new Date());

        taskUnitMapper.insert(taskUnit);

        return taskUnit;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(taskUnitVO.getTuid());

        taskUnitMapper.delete(taskUnit);

        // TODO：删除相关联的内容

        // 删除相关联的连接
        TaskUnitConnect taskUnitConnect = new TaskUnitConnect();
        taskUnitConnect.setTid(taskUnitVO.getTid());
        taskUnitConnect.setSourceTuid(taskUnitVO.getTuid());
        taskUnitConnectService.taskUnitDelete(taskUnitConnect);
    }

    @Override
    public void deleteMultiple(List<Integer> deleteTidList) {
        taskUnitMapper.deleteMultiple(deleteTidList);

        // TODO：删除相关联的内容
    }

    @Override
    public void update(TaskUnitVO taskUnitVO) {
        TaskUnit taskUnit = new TaskUnit();
        taskUnit.setTuid(taskUnitVO.getTuid());
        taskUnit.setLeftDis(taskUnitVO.getLeft());
        taskUnit.setTopDis(taskUnitVO.getTop());

        taskUnitMapper.updateByPrimaryKeySelective(taskUnit);
    }
}
