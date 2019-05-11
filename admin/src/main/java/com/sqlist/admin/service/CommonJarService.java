package com.sqlist.admin.service;

import com.sqlist.admin.domain.CommonJar;
import com.sqlist.admin.vo.CommonJarVO;
import com.sqlist.admin.vo.PageVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/2 2:21
 * @description
 **/
public interface CommonJarService {

    /**
     * 获取 common jar 列表
     * @param pageVO
     * @return
     */
    Map<String, Object> list(PageVO pageVO);

    /**
     * 上传
     * @param commonJarVO
     */
    void upload(CommonJarVO commonJarVO);

    /**
     * 下载
     * @param response
     * @param cjid
     */
    void download(HttpServletResponse response, Integer cjid);

    /**
     * 删除
     * @param cjidList
     */
    void delete(List<Integer> cjidList);
}
