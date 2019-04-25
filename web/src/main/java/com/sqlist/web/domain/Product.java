package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/25 10:31
 * @description
 **/
public class Product {
    /**
     * 产品id
     */
    @Id
    private Integer pid;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品key
     */
    @Column(name = "product_key")
    private String productKey;

    /**
     * 产品secret
     */
    @Column(name = "product_secret")
    private String productSecret;

    /**
     * 产品创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 产品状态
     */
    private String status;

    /**
     * 产品对应的Topic
     */
    private String topic;

    /**
     * 产品属于的uid
     */
    private Integer uid;

    /**
     * 获取产品id
     *
     * @return pid - 产品id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置产品id
     *
     * @param pid 产品id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取产品名称
     *
     * @return name - 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     *
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取产品key
     *
     * @return product_key - 产品key
     */
    public String getProductKey() {
        return productKey;
    }

    /**
     * 设置产品key
     *
     * @param productKey 产品key
     */
    public void setProductKey(String productKey) {
        this.productKey = productKey == null ? null : productKey.trim();
    }

    /**
     * 获取产品secret
     *
     * @return product_secret - 产品secret
     */
    public String getProductSecret() {
        return productSecret;
    }

    /**
     * 设置产品secret
     *
     * @param productSecret 产品secret
     */
    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret == null ? null : productSecret.trim();
    }

    /**
     * 获取产品创建时间
     *
     * @return create_time - 产品创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置产品创建时间
     *
     * @param createTime 产品创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取产品状态
     *
     * @return status - 产品状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置产品状态
     *
     * @param status 产品状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取产品对应的Topic
     *
     * @return topic - 产品对应的Topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置产品对应的Topic
     *
     * @param topic 产品对应的Topic
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * 获取产品属于的uid
     *
     * @return uid - 产品属于的uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置产品属于的uid
     *
     * @param uid 产品属于的uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}