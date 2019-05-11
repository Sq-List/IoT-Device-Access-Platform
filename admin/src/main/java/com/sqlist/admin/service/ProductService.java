package com.sqlist.admin.service;

import com.sqlist.admin.domain.Product;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.vo.PageVO;
import com.sqlist.admin.vo.ProductVO;

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

    /**
     * 获取产品详细信息
     * @param product
     * @return
     */
    Product detail(Product product);

    /**
     * 删除产品
     * @param pidList
     */
    void deleteMultiple(List<Integer> pidList);

    /**
     * 获取
     * @param pid
     * @return
     */
    Product get(Integer pid);
}
