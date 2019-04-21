package com.sqlist.web.domain;

import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/20 22:50
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
    private Integer sourceTuid;

    /**
     * 连接的终点tuid
     */
    @Column(name = "target_tuid")
    private Integer targetTuid;

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
    public Integer getSourceTuid() {
        return sourceTuid;
    }

    /**
     * 设置连接的起点tuid
     *
     * @param sourceTuid 连接的起点tuid
     */
    public void setSourceTuid(Integer sourceTuid) {
        this.sourceTuid = sourceTuid;
    }

    /**
     * 获取连接的终点tuid
     *
     * @return target_tuid - 连接的终点tuid
     */
    public Integer getTargetTuid() {
        return targetTuid;
    }

    /**
     * 设置连接的终点tuid
     *
     * @param targetTuid 连接的终点tuid
     */
    public void setTargetTuid(Integer targetTuid) {
        this.targetTuid = targetTuid;
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