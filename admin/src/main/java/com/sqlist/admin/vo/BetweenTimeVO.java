package com.sqlist.admin.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author SqList
 * @date 2019/5/22 20:12
 * @description
 **/
@Data
@ToString
public class BetweenTimeVO {

    private Integer tid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
