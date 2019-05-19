package com.sqlist.clientweb.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

@Table(name = "temperature_alarm")
public class TemperatureAlarm {
    @Id
    private Integer taid;

    private Boolean alarm;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /**
     * @return taid
     */
    public Integer getTaid() {
        return taid;
    }

    /**
     * @param taid
     */
    public void setTaid(Integer taid) {
        this.taid = taid;
    }

    /**
     * @return alarm
     */
    public Boolean getAlarm() {
        return alarm;
    }

    /**
     * @param alarm
     */
    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
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