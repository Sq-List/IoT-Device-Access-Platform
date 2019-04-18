package com.sqlist.web.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/11 22:27
 * @description
 **/
@Data
public class LoginVO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
