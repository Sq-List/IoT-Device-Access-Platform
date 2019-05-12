package com.sqlist.web.vo.search;

import com.sqlist.web.vo.PageVO;
import lombok.Data;
import lombok.ToString;

/**
 * @author SqList
 * @date 2019/5/13 2:05
 * @description
 **/
@Data
@ToString
public class DeviceSearchVO extends PageVO {

    private String name;

    private String deviceKey;

    private String status;
}
