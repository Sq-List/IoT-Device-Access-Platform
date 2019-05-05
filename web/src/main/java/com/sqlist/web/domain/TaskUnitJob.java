package com.sqlist.web.domain;

import javax.persistence.Column;

/**
 * @author SqList
 * @date 2019/5/5 14:49
 * @description
 **/
public class TaskUnitJob extends TaskUnit {

    /**
     * 任务单元状态
     */
    private String status;

    /**
     * 任务单元提交到flink的job_id
     */
    @Column(name = "job_id")
    private String jobId;

    /**
     * 获取任务单元状态
     *
     * @return status - 任务单元状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置任务单元状态
     *
     * @param status 任务单元状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取任务单元提交到flink的job_id
     *
     * @return job_id - 任务单元提交到flink的job_id
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置任务单元提交到flink的job_id
     *
     * @param jobId 任务单元提交到flink的job_id
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }
}
