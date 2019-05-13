package com.sqlist.access.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

@Table(name = "task_send_sum")
public class TaskSendSum {
    @Id
    private Integer tssid;

    private Integer count;

    @JSONField(format = "yyyy-MM-dd HH")
    private Date time;

    private Integer tid;

    /**
     * @return tssid
     */
    public Integer getTssid() {
        return tssid;
    }

    /**
     * @param tssid
     */
    public void setTssid(Integer tssid) {
        this.tssid = tssid;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return tid
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * @param tid
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }
}