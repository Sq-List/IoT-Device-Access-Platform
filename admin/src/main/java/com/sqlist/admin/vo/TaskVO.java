package com.sqlist.admin.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/22 15:09
 * @description
 **/
@Data
@ToString
public class TaskVO {

    @NotNull
    private String name;
}
