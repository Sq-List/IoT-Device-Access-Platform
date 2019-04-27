package com.sqlist.web.mapper;

import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SqList
 * @date 2019/4/25 10:31
 * @description
 **/
public interface ProductMapper extends MyMapper<Product> {

    /**
     * 删除多个产品
     * @param user
     * @param pidList
     */
    void deleteMultiple(@Param("user") User user, @Param("pidList") List<Integer> pidList);
}