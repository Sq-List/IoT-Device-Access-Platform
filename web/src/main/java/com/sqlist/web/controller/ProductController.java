package com.sqlist.web.controller;

import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.ProductService;
import com.sqlist.web.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/25 10:32
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, @Validated PageVO pageVO) {
        Map<String, Object> map = productService.list(user, pageVO);
        return Result.success(map);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user) {
        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete() {
        return Result.success(null);
    }
}
