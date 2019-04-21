package com.sqlist.web.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/21 0:00
 * @description
 **/
@Data
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
