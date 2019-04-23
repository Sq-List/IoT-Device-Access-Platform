package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/22 15:01
 * @description
 **/
@Data
@ToString
public class TaskUnitVO {

    @NotNull(groups = Delete.class)
    private String tuid;

    @NotNull(groups = Add.class)
    private String type;

    @NotNull(groups = Add.class)
    private Integer tid;

    public interface Add {}

    public interface Delete {}
}
