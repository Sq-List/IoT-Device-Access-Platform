package com.sqlist.web.service.impl.task;

import com.sqlist.web.constants.TaskJobStatus;
import com.sqlist.web.domain.TaskUnitHandle;
import com.sqlist.web.mapper.TaskUnitHandleMapper;
import com.sqlist.web.service.task.TaskService;
import com.sqlist.web.service.task.TaskUnitHandleService;
import com.sqlist.web.vo.TaskUnitHandleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/1 2:43
 * @description
 **/
@Service
public class TaskUnitHandleServiceImpl implements TaskUnitHandleService {

    @Autowired
    private TaskUnitHandleMapper taskUnitHandleMapper;

    @Autowired
    private TaskService taskService;

    @Override
    public TaskUnitHandle get(String tuid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTuid(tuid);
        return taskUnitHandleMapper.selectByPrimaryKey(taskUnitHandle);
    }

    @Override
    public List<TaskUnitHandle> list(Integer tid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTid(tid);
        return taskUnitHandleMapper.select(taskUnitHandle);
    }

    @Override
    public void updateDetail(TaskUnitHandleVO taskUnitHandleVO) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTuid(taskUnitHandleVO.getTuid());
        taskUnitHandle.setStatus(TaskJobStatus.UNUSE.name());
        taskUnitHandle.setType(taskUnitHandleVO.getType());
        taskUnitHandle.setFid(taskUnitHandleVO.getFid());
        taskUnitHandle.setContent(taskUnitHandleVO.getContent());

        taskUnitHandleMapper.updateByPrimaryKeySelective(taskUnitHandle);

        // 更新task的updateTime
        taskService.updateUpdateTime(taskUnitHandleVO.getTid());
    }

    @Override
    public List<TaskUnitHandle> countDetailNull(Integer tid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTid(tid);
        taskUnitHandle.setType("");
        taskUnitHandle.setFid(0);
        return taskUnitHandleMapper.select(taskUnitHandle);
    }

    @Override
    public Integer count(Integer tid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setTid(tid);
        return taskUnitHandleMapper.selectCount(taskUnitHandle);
    }

    @Override
    public Boolean fileIsUsed(Integer fid) {
        TaskUnitHandle taskUnitHandle = new TaskUnitHandle();
        taskUnitHandle.setFid(fid);
        return taskUnitHandleMapper.selectCount(taskUnitHandle) != 0;
    }

    @Override
    public void update(TaskUnitHandle taskUnitHandle) {
        taskUnitHandleMapper.updateByPrimaryKeySelective(taskUnitHandle);
    }
}
