package com.sqlist.web.service.impl.task;

import com.sqlist.web.domain.TaskUnit;
import com.sqlist.web.domain.TaskUnitHandle;
import com.sqlist.web.domain.TaskUnitJob;
import com.sqlist.web.domain.TaskUnitOutput;
import com.sqlist.web.mapper.TaskJobMapper;
import com.sqlist.web.service.task.TaskJobService;
import com.sqlist.web.service.task.TaskUnitHandleService;
import com.sqlist.web.service.task.TaskUnitOutputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/6 0:46
 * @description
 **/
@Slf4j
@Service
public class TaskJobServiceImpl implements TaskJobService {

    @Autowired
    private TaskJobMapper taskJobMapper;

    @Autowired
    private TaskUnitHandleService taskUnitHandleService;

    @Autowired
    private TaskUnitOutputService taskUnitOutputService;

    @Override
    public List<TaskUnitJob> getStartingJob(Integer tid) {
        return taskJobMapper.selectStartingJob(tid);
    }

    @Override
    public void update(TaskUnitJob job) {
        if (job.getTuid().startsWith(TaskUnit.HANDLE)) {
            TaskUnitHandle handle = new TaskUnitHandle();
            handle.setTuid(job.getTuid());
            handle.setStatus(job.getStatus());
            handle.setJobId(job.getJobId());
            taskUnitHandleService.update(handle);
        } else if (job.getTuid().startsWith(TaskUnit.OUTPUT)) {
            TaskUnitOutput output = new TaskUnitOutput();
            output.setTuid(job.getTuid());
            output.setStatus(job.getStatus());
            output.setJobId(job.getJobId());
            taskUnitOutputService.update(output);
        }
    }
}
