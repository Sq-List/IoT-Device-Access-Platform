package com.sqlist.web.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * @author SqList
 * @date 2019/5/1 0:52
 * @description
 **/
@Table(name = "task_unit_input")
public class TaskUnitInput extends TaskUnit {

    /**
     * 输入所选的产品
     */
    private Integer pid;

    /**
     * 获取输入所选的产品
     *
     * @return pid - 输入所选的产品
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置输入所选的产品
     *
     * @param pid 输入所选的产品
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
}