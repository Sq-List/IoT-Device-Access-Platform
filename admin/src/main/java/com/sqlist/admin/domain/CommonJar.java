package com.sqlist.admin.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
@Table(name = "common_jar")
public class CommonJar {
    @Id
    private Integer cjid;

    /**
     * jar名称
     */
    private String name;

    /**
     * jar路径
     */
    private String path;

    /**
     * jar类型(handle, output)
     */
    @Column(name = "task_unit_type")
    private String taskUnitType;

    /**
     * handle(SQL, python...) output(http, kafka...)
     */
    private String type;

    /**
     * jar执行的入口类
     */
    @Column(name = "main_class")
    private String mainClass;

    @Column(name = "jar_id")
    private String jarId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return cjid
     */
    public Integer getCjid() {
        return cjid;
    }

    /**
     * @param cjid
     */
    public void setCjid(Integer cjid) {
        this.cjid = cjid;
    }

    /**
     * 获取jar名称
     *
     * @return name - jar名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置jar名称
     *
     * @param name jar名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取jar路径
     *
     * @return path - jar路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置jar路径
     *
     * @param path jar路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取jar类型(handle, output)
     *
     * @return task_unit_type - jar类型(handle, output)
     */
    public String getTaskUnitType() {
        return taskUnitType;
    }

    /**
     * 设置jar类型(handle, output)
     *
     * @param taskUnitType jar类型(handle, output)
     */
    public void setTaskUnitType(String taskUnitType) {
        this.taskUnitType = taskUnitType == null ? null : taskUnitType.trim();
    }

    /**
     * 获取handle(SQL, python...) output(http, kafka...)
     *
     * @return type - handle(SQL, python...) output(http, kafka...)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置handle(SQL, python...) output(http, kafka...)
     *
     * @param type handle(SQL, python...) output(http, kafka...)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取jar执行的入口类
     *
     * @return main_class - jar执行的入口类
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     * 设置jar执行的入口类
     *
     * @param mainClass jar执行的入口类
     */
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass == null ? null : mainClass.trim();
    }

    public String getJarId() {
        return jarId;
    }

    public void setJarId(String jarId) {
        this.jarId = jarId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}