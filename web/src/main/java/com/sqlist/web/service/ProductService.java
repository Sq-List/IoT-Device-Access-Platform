package com.sqlist.web.service;

import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.PageVO;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/25 18:14
 * @description
 **/
public interface ProductService {

    /**
     * 获取产品
     * @param user
     * @param pageVO
     * @return
     */
    Map<String, Object> list(User user, PageVO pageVO);
}
