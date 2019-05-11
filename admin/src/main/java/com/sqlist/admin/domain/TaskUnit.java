package com.sqlist.admin.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
public class TaskUnit {

    public static final String INPUT = "input";
    public static final String HANDLE = "handle";
    public static final String OUTPUT = "output";

    /**
     * 任务单元id,uuid
     */
    @Id
    private String tuid;

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
     * 任务单元创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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