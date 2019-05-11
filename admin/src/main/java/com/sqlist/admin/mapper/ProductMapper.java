package com.sqlist.admin.mapper;

import com.sqlist.admin.domain.Product;
import com.sqlist.admin.util.MyMapper;
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
     * @param pidList
     */
    void deleteMultiple(@Param("pidList") List<Integer> pidList);
}