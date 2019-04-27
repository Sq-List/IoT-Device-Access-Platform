package com.sqlist.web.controller;

import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.FileService;
import com.sqlist.web.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SqList
 * @date 2019/4/28 1:19
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, @Validated PageVO pageVO) {
        return Result.success(fileService.list(user, pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result upload(User user, String name, MultipartFile file) {
        if (file.isEmpty()) {
            throw new GlobalException(CodeMsg.UPLOAD_FILE_EMPET);
        }

//        String suffix ;
        return Result.success(null);
    }
}
