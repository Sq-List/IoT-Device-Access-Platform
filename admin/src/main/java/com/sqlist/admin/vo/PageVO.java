package com.sqlist.admin.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/21 0:00
 * @description
 **/
@Data
@ToString
public class PageVO {

    /**
     * 当前页码
     */
    @NotNull
    private Integer page;

    /**
     * 每页数据量
     */
    @NotNull
    private Integer limit;
}
