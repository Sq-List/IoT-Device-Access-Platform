package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/23 21:46
 * @description
 **/
@Data
@ToString
public class TaskUnitConnectVO {

    @NotNull(groups = {Update.class})
    private String oldSourceTuid;

    @NotNull(groups = {Update.class})
    private String oldTargetTuid;

    @NotNull(groups = {Add.class, Delete.class, Update.class})
    private String sourceTuid;

    @NotNull(groups = {Add.class, Delete.class, Update.class})
    private String targetTuid;

    @NotNull(groups = {Add.class, Delete.class, Update.class})
    private Integer tid;

    public interface Add {}

    public interface Delete {}

    public interface Update {}
}
