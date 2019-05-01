package com.sqlist.web.domain;

import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
@Table(name = "task_unit_handle")
public class TaskUnitHandle extends TaskUnit {

    /**
     * java, sql...
     */
    private String type;

    /**
     * type为java时, 指向file表
     */
    private Integer fid;

    private String content;

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
     * 获取java, sql...
     *
     * @return type - java, sql...
     */
    public String getType() {
        return type;
    }

    /**
     * 设置java, sql...
     *
     * @param type java, sql...
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取type为java时, 指向file表
     *
     * @return fid - type为java时, 指向file表
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置type为java时, 指向file表
     *
     * @param fid type为java时, 指向file表
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

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