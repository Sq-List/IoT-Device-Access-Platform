package com.sqlist.web.domain;

import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/20 22:50
 * @description
 **/
@Table(name = "task_unit")
public class TaskUnit {
    /**
     * 任务单元id
     */
    @Id
    private Integer tuid;

    /**
     * 任务单元类型
     */
    private String type;

    /**
     * 任务单元类型id
     */
    @Column(name = "typd_id")
    private Integer typdId;

    /**
     * 任务单元状态
     */
    private Byte status;

    /**
     * 任务单元提交到flink的job_id
     */
    @Column(name = "job_id")
    private String jobId;

    /**
     * 任务单元所在的任务
     */
    private Integer tid;

    /**
     * 获取任务单元id
     *
     * @return tuid - 任务单元id
     */
    public Integer getTuid() {
        return tuid;
    }

    /**
     * 设置任务单元id
     *
     * @param tuid 任务单元id
     */
    public void setTuid(Integer tuid) {
        this.tuid = tuid;
    }

    /**
     * 获取任务单元类型
     *
     * @return type - 任务单元类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置任务单元类型
     *
     * @param type 任务单元类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取任务单元类型id
     *
     * @return typd_id - 任务单元类型id
     */
    public Integer getTypdId() {
        return typdId;
    }

    /**
     * 设置任务单元类型id
     *
     * @param typdId 任务单元类型id
     */
    public void setTypdId(Integer typdId) {
        this.typdId = typdId;
    }

    /**
     * 获取任务单元状态
     *
     * @return status - 任务单元状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置任务单元状态
     *
     * @param status 任务单元状态
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * 获取任务单元所在的任务
     *
     * @return tid - 任务单元所在的任务
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置任务单元所在的任务
     *
     * @param tid 任务单元所在的任务
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }
}