package com.sqlist.admin.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author SqList
 * @date 2019/4/28 1:19
 * @description
 **/
public class Task {
    /**
     * 任务id
     */
    @Id
    private Integer tid;

    /**
     * 任务名字
     */
    private String name;

    /**
     * 任务状态
     */
    private String status;

    /**
     * 任务创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 任务更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 任务对应的用户id
     */
    private Integer uid;

    /**
     * 获取任务id
     *
     * @return tid - 任务id
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置任务id
     *
     * @param tid 任务id
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取任务名字
     *
     * @return name - 任务名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务名字
     *
     * @param name 任务名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取任务状态
     *
     * @return status - 任务状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置任务状态
     *
     * @param status 任务状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取任务创建时间
     *
     * @return create_time - 任务创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置任务创建时间
     *
     * @param createTime 任务创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取任务更新时间
     *
     * @return update_time - 任务更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置任务更新时间
     *
     * @param updateTime 任务更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取任务对应的用户id
     *
     * @return uid - 任务对应的用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置任务对应的用户id
     *
     * @param uid 任务对应的用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}