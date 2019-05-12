package com.sqlist.web.service;

import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.ProductVO;
import com.sqlist.web.vo.search.ProductSearchVO;

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
     * @param productSearchVO
     * @return
     */
    Map<String, Object> list(User user, ProductSearchVO productSearchVO);

    /**
     * 获取产品详细信息
     * @param product
     * @return
     */
    Product detail(Product product);

    /**
     * 添加产品
     * @param user
     * @param productVO
     */
    void add(User user, ProductVO productVO);

    /**
     * 删除产品
     * @param user
     * @param pidList
     */
    void deleteMultiple(User user, List<Integer> pidList);

    /**
     * 获取
     * @param pid
     * @return
     */
    Product get(Integer pid);
}
