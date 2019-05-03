package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/5/3 17:32
 * @description
 **/
@Data
@ToString
public class TaskUnitHandleVO {

    private String tuid;

    @NotNull
    private String type;

    @NotNull
    private Integer fid;

    @NotNull
    private Integer tid;

    private String content;
}
