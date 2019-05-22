package com.sqlist.admin.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "task_send_sum")
public class TaskSendSum {
    @Id
    private Integer tssid;

    private Integer count;

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