package com.sqlist.access.service.impl;

import com.sqlist.access.domain.Product;
import com.sqlist.access.mapper.ProductMapper;
import com.sqlist.access.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SqList
 * @date 2019/5/10 2:02
 * @description
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product get(String productKey) {
        Product product = new Product();
        product.setProductKey(productKey);
        return productMapper.selectOne(product);
    }
}
