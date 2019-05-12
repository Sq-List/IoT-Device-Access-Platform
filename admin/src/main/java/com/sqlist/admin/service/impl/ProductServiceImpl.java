package com.sqlist.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.admin.domain.Product;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.mapper.ProductMapper;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.service.ProductService;
import com.sqlist.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private DeviceService deviceService;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        Product product = new Product();
        if (user != null) {
            product.setUid(user.getUid());
        }

        HashMap<String, Object> map = new HashMap<>();

        if (pageVO.getLimit() != -1) {
            PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        }
        List<Map<String, String>> productList = productMapper.selectWithUser(product);

        if (pageVO.getLimit() != -1) {
            map.put("total", ((Page)productList).getTotal());
        }
        map.put("list", productList);

        return map;
    }

    @Override
    public Product detail(Product product) {
        product = productMapper.selectOne(product);

        return product;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteMultiple(List<Integer> pidList) {
        productMapper.deleteMultiple(pidList);

        // 原本属于此产品的设备直接删除
        deviceService.deleteMultipleByPid(pidList);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteMultiple(Integer uid) {
        Product product = new Product();
        product.setUid(uid);
        List<Product> productList = productMapper.select(product);

        deleteMultiple(productList.stream()
                                .map(Product::getPid)
                                .collect(Collectors.toList()));
    }

    @Override
    public Product get(Integer pid) {
        Product product = new Product();
        product.setPid(pid);
        return productMapper.selectByPrimaryKey(product);
    }

    @Override
    public Integer count() {
        return productMapper.selectCount(new Product());
    }
}
