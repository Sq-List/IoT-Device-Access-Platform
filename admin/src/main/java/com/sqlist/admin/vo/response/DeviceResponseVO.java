package com.sqlist.admin.vo.response;

import com.sqlist.admin.domain.Device;
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
