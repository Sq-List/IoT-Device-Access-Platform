package com.sqlist.admin.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/25 19:47
 * @description
 **/
@Data
@ToString
public class ProductVO {

    @NotNull
    private String name;
}
