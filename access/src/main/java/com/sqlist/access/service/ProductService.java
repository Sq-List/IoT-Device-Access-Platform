package com.sqlist.access.service;

import com.sqlist.access.domain.Product;

/**
 * @author SqList
 * @date 2019/5/10 2:01
 * @description
 **/
public interface ProductService {

    Product get(String productKey);
}
