package com.sqlist.admin.service;

import com.sqlist.admin.domain.User;
import com.sqlist.admin.vo.PageVO;

import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/11 19:57
 * @description
 **/
public interface UserService {

    /**
     * 列出所有用户
     * @param pageVO
     * @return
     */
    Map<String, Object> list(PageVO pageVO);

    /**
     * 获取用户详细信息
     * @param uid
     * @return
     */
    User get(Integer uid);

    /**
     * 删除用户
     * @param uid
     */
    void delete(Integer uid);
}
