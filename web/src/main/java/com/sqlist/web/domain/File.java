package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/4/28 1:19
 * @description
 **/
public class File {
    /**
     * 文件id
     */
    @Id
    private Integer fid;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件扩展名
     */
    private String extensions;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 文件所属用户id
     */
    private Integer uid;

    /**
     * 获取文件id
     *
     * @return fid - 文件id
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置文件id
     *
     * @param fid 文件id
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 获取文件名称
     *
     * @return name - 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名称
     *
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取文件扩展名
     *
     * @return extensions - 文件扩展名
     */
    public String getExtensions() {
        return extensions;
    }

    /**
     * 设置文件扩展名
     *
     * @param extensions 文件扩展名
     */
    public void setExtensions(String extensions) {
        this.extensions = extensions == null ? null : extensions.trim();
    }

    /**
     * 获取文件路径
     *
     * @return path - 文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件路径
     *
     * @param path 文件路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取上传时间
     *
     * @return upload_time - 上传时间
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 设置上传时间
     *
     * @param uploadTime 上传时间
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 获取文件所属用户id
     *
     * @return uid - 文件所属用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置文件所属用户id
     *
     * @param uid 文件所属用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}