package com.sqlist.web.vo;

import com.sqlist.web.domain.Device;
import lombok.Data;
import lombok.ToString;

/**
 * @author SqList
 * @date 2019/5/10 17:23
 * @description
 **/
@Data
@ToString
public class DeviceResponseVO extends Device {

    private String productName;
}
