package com.sqlist.access.domain;

import java.util.Date;
import javax.persistence.*;

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

    /**
     * 设备key
     */
    @Column(name = "device_key")
    private String deviceKey;

    /**
     * 设备secret
     */
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
     * 设备属于的产品id，0说明还没有所属产品
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
     * 获取设备key
     *
     * @return device_key - 设备key
     */
    public String getDeviceKey() {
        return deviceKey;
    }

    /**
     * 设置设备key
     *
     * @param deviceKey 设备key
     */
    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey == null ? null : deviceKey.trim();
    }

    /**
     * 获取设备secret
     *
     * @return device_secret - 设备secret
     */
    public String getDeviceSecret() {
        return deviceSecret;
    }

    /**
     * 设置设备secret
     *
     * @param deviceSecret 设备secret
     */
    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret == null ? null : deviceSecret.trim();
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
     * 获取设备属于的产品id，0说明还没有所属产品
     *
     * @return pid - 设备属于的产品id，0说明还没有所属产品
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置设备属于的产品id，0说明还没有所属产品
     *
     * @param pid 设备属于的产品id，0说明还没有所属产品
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取设备属于的用户id
     *
     * @return uid - 设备属于的用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置设备属于的用户id
     *
     * @param uid 设备属于的用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}