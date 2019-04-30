package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/28 1:19
 * @description
 **/
@Table(name = "task_unit_connect")
public class TaskUnitConnect {
    /**
     * 任务单元连接id
     */
    @Id
    private Integer tucid;

    /**
     * 连接的起点tuid
     */
    @Column(name = "source_tuid")
    private String sourceTuid;

    /**
     * 连接的终点tuid
     */
    @Column(name = "target_tuid")
    private String targetTuid;

    /**
     * 连接的创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 连接所属的任务id
     */
    private Integer tid;

    /**
     * 获取任务单元连接id
     *
     * @return tucid - 任务单元连接id
     */
    public Integer getTucid() {
        return tucid;
    }

    /**
     * 设置任务单元连接id
     *
     * @param tucid 任务单元连接id
     */
    public void setTucid(Integer tucid) {
        this.tucid = tucid;
    }

    /**
     * 获取连接的起点tuid
     *
     * @return source_tuid - 连接的起点tuid
     */
    public String getSourceTuid() {
        return sourceTuid;
    }

    /**
     * 设置连接的起点tuid
     *
     * @param sourceTuid 连接的起点tuid
     */
    public void setSourceTuid(String sourceTuid) {
        this.sourceTuid = sourceTuid == null ? null : sourceTuid.trim();
    }

    /**
     * 获取连接的终点tuid
     *
     * @return target_tuid - 连接的终点tuid
     */
    public String getTargetTuid() {
        return targetTuid;
    }

    /**
     * 设置连接的终点tuid
     *
     * @param targetTuid 连接的终点tuid
     */
    public void setTargetTuid(String targetTuid) {
        this.targetTuid = targetTuid == null ? null : targetTuid.trim();
    }

    /**
     * 获取连接的创建时间
     *
     * @return create_time - 连接的创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置连接的创建时间
     *
     * @param createTime 连接的创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取连接所属的任务id
     *
     * @return tid - 连接所属的任务id
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置连接所属的任务id
     *
     * @param tid 连接所属的任务id
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }
}