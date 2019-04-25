package com.sqlist.web.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/22 15:09
 * @description
 **/
@Data
public class TaskVO {

    @NotNull
    private String name;
}
