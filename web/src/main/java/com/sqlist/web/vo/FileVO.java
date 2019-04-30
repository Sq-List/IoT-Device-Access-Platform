package com.sqlist.web.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author SqList
 * @date 2019/4/28 1:32
 * @description
 **/
@Data
@ToString
public class FileVO {

    @NotNull
    private String name;

    @NotNull
    private String mainClass;

    @NotNull(message = "上传文件空，上传失败")
    private MultipartFile file;
}
