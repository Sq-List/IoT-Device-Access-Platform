package com.sqlist.web.domain;

import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
@Table(name = "task_unit_handle")
public class TaskUnitHandle extends TaskUnitJob {

    /**
     * java, sql...
     */
    private String type;

    /**
     * type为java时, 指向file表
     */
    private Integer fid;

    private String content;

    /**
     * 获取java, sql...
     *
     * @return type - java, sql...
     */
    public String getType() {
        return type;
    }

    /**
     * 设置java, sql...
     *
     * @param type java, sql...
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取type为java时, 指向file表
     *
     * @return fid - type为java时, 指向file表
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置type为java时, 指向file表
     *
     * @param fid type为java时, 指向file表
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}