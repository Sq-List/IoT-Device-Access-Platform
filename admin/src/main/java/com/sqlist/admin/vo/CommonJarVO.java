package com.sqlist.admin.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/5/12 1:27
 * @description
 **/
@Data
@ToString
public class CommonJarVO {

    @NotNull
    private String name;

    @NotNull
    private String taskUnitType;

    @NotNull
    private String type;

    @NotNull
    private String mainClass;

    @NotNull(message = "上传文件空，上传失败")
    private MultipartFile file;
}
