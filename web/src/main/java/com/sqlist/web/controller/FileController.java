package com.sqlist.web.controller;

import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.FileService;
import com.sqlist.web.vo.FileVO;
import com.sqlist.web.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/{extensions}", method = RequestMethod.GET)
    public Result list(User user, @PathVariable("extensions") String extensions) {
        return Result.success(fileService.list(user, extensions));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result upload(User user, @Validated FileVO fileVO) {
        fileService.upload(user, fileVO);
        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @RequestBody List<Integer> fidList) {
        fileService.delete(user, fidList);
        return Result.success(null);
    }
}
