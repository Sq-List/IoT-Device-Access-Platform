package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/5/2 1:09
 * @description
 **/
@Data
@ToString
public class TaskUnitInputVO {

    private String tuid;

    @NotNull
    private Integer pid;
}
