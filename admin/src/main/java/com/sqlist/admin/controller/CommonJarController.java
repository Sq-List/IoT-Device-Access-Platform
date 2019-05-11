package com.sqlist.admin.controller;

import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.CommonJarService;
import com.sqlist.admin.vo.CommonJarVO;
import com.sqlist.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author SqList
 * @date 2019/5/12 1:21
 * @description
 **/
@RestController
@RequestMapping("/api/commonJar")
public class CommonJarController {

    @Autowired
    private CommonJarService commonJarService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        return Result.success(commonJarService.list(pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result upload(@Validated CommonJarVO commonJarVO) {
        commonJarService.upload(commonJarVO);
        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(@RequestBody List<Integer> cjidList) {
        commonJarService.delete(cjidList);
        return Result.success(null);
    }

    @RequestMapping(value = "/{cjid}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("cjid") Integer cjid) {
        commonJarService.download(response, cjid);
    }
}
