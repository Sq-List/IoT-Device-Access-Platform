package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/5/3 17:54
 * @description
 **/
@Data
@ToString
public class TaskUnitOutputVO {

    @NotNull
    private String tuid;

    @NotNull
    private String type;

    @NotNull
    private String ip;

    @NotNull
    private String port;

    private String url;

    @NotNull
    private Integer tid;
}
