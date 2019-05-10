package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/26 1:28
 * @description
 **/
@Data
@ToString
public class DeviceVO {

    @NotNull(groups = Add.class)
    private String name;

    @NotNull(groups = {Product.class, Add.class})
    private Integer pid;

    @NotNull(groups = {Product.class, Device.class})
    private Integer uid;

    private String deviceKey;

    public interface Product {}

    public interface Device {}

    public interface Add {}
}
