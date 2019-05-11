package com.sqlist.admin.vo;

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

    @NotNull(groups = {Delete.class, Update.class, Delete.class})
    private String tuid;

    @NotNull(groups = {Add.class, Update.class, Delete.class})
    private String type;

    @NotNull(groups = {Add.class, Update.class})
    private Integer top;

    @NotNull(groups = {Add.class, Update.class})
    private Integer left;

    @NotNull(groups = {Add.class, Delete.class, Update.class})
    private Integer tid;

    public interface Add {}

    public interface Delete {}

    public interface Update {}
}
