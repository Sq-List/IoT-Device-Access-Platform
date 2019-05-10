package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/25 10:31
 * @description
 **/
public class Device {
    /**
     * 设备id
     */
    @Id
    private Integer did;

    /**
     * 设备名称
     */
    private String name;

    @Column(name = "device_key")
    private String deviceKey;

    @Column(name = "device_secret")
    private String deviceSecret;

    /**
     * 设备状态
     */
    private String status;

    /**
     * 设备创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 设备激活时间
     */
    @Column(name = "active_time")
    private Date activeTime;

    /**
     * 设备最后一次上线时间
     */
    @Column(name = "last_time")
    private Date lastTime;

    /**
     * 设备属于的产品id
     */
    private Integer pid;

    /**
     * 设备属于的用户id
     */
    private Integer uid;

    /**
     * 获取设备id
     *
     * @return did - 设备id
     */
    public Integer getDid() {
        return did;
    }

    /**
     * 设置设备id
     *
     * @param did 设备id
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * 获取设备名称
     *
     * @return name - 设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名称
     *
     * @param name 设备名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取设备状态
     *
     * @return status - 设备状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置设备状态
     *
     * @param status 设备状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    /**
     * 获取设备创建时间
     *
     * @return create_time - 设备创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置设备创建时间
     *
     * @param createTime 设备创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取设备激活时间
     *
     * @return active_time - 设备激活时间
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * 设置设备激活时间
     *
     * @param activeTime 设备激活时间
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * 获取设备最后一次上线时间
     *
     * @return last_time - 设备最后一次上线时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置设备最后一次上线时间
     *
     * @param lastTime 设备最后一次上线时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取设备属于的产品id
     *
     * @return pid - 设备属于的产品id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置设备属于的产品id
     *
     * @param pid 设备属于的产品id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}