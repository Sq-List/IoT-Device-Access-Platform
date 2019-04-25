package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "task_unit")
public class TaskUnit {
    /**
     * 任务单元id,uuid
     */
    @Id
    private String tuid;

    /**
     * 任务单元类型
     */
    private String type;

    /**
     * 任务单元位置
     */
    @Column(name = "left_dis")
    private Integer leftDis;

    /**
     * 任务单元位置
     */
    @Column(name = "top_dis")
    private Integer topDis;

    /**
     * 任务单元状态
     */
    private String status;

    /**
     * 任务单元创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取任务单元id,uuid
     *
     * @return tuid - 任务单元id,uuid
     */
    public String getTuid() {
        return tuid;
    }

    /**
     * 设置任务单元id,uuid
     *
     * @param tuid 任务单元id,uuid
     */
    public void setTuid(String tuid) {
        this.tuid = tuid == null ? null : tuid.trim();
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
     * 获取任务单元位置
     *
     * @return left_dis - 任务单元位置
     */
    public Integer getLeftDis() {
        return leftDis;
    }

    /**
     * 设置任务单元位置
     *
     * @param leftDis 任务单元位置
     */
    public void setLeftDis(Integer leftDis) {
        this.leftDis = leftDis;
    }

    /**
     * 获取任务单元位置
     *
     * @return top_dis - 任务单元位置
     */
    public Integer getTopDis() {
        return topDis;
    }

    /**
     * 设置任务单元位置
     *
     * @param topDis 任务单元位置
     */
    public void setTopDis(Integer topDis) {
        this.topDis = topDis;
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
     * 获取任务单元创建时间
     *
     * @return create_time - 任务单元创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置任务单元创建时间
     *
     * @param createTime 任务单元创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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