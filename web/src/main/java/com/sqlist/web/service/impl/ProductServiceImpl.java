package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.mapper.ProductMapper;
import com.sqlist.web.service.ProductService;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/25 18:16
 * @description
 **/
@Service
public class ProductServiceImpl implements ProductService {

    public static final String SPLIT = "-";

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        Product product = new Product();
        product.setUid(user.getUid());

        HashMap<String, Object> map = new HashMap<>();

        PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        List<Product> productList = productMapper.select(product);

        map.put("total", ((Page)productList).getTotal());
        map.put("list", productList);

        return map;
    }

    @Override
    public Product detail(Integer pid) {
        Product product = new Product();
        product.setPid(pid);

        product = productMapper.selectOne(product);

        return product;
    }

    @Override
    public void add(User user, ProductVO productVO) {
        Product product = new Product();
        product.setUid(user.getUid());
        product.setName(productVO.getName());
        product.setCreateTime(new Date());
        product.setTopic(user.getUsername() + SPLIT + product.getName());

        productMapper.insert(product);
    }
}
