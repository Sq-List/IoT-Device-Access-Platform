package com.sqlist.admin.controller;

import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.FileService;
import com.sqlist.admin.vo.FileVO;
import com.sqlist.admin.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author SqList
 * @date 2019/5/12 0:56
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        return Result.success(fileService.list(null, pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(@RequestBody List<Integer> fidList) {
        fileService.delete(fidList);
        return Result.success(null);
    }

    @RequestMapping(value = "/{fid}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fid") Integer fid) throws UnsupportedEncodingException {
        log.info("下载文件 fid: {}", fid);
        fileService.download(response, fid);
    }
}
