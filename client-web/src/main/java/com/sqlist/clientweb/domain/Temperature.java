package com.sqlist.clientweb.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

public class Temperature {
    @Id
    private Integer tid;

    private Integer temperature;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

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

    /**
     * @return temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * @param temperature
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
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
}