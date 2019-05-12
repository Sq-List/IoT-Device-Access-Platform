package com.sqlist.admin.service;

import com.sqlist.admin.domain.File;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.vo.FileVO;
import com.sqlist.admin.vo.PageVO;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/28 1:29
 * @description
 **/
public interface FileService {

    /**
     * 获取 上传文件列表
     * @param user
     * @param pageVO
     * @return
     */
    Map<String, Object> list(User user, PageVO pageVO);

    /**
     * 获取
     * @param fid
     * @return
     */
    File get(Integer fid);

    /**
     * 下载文件
     * @param response
     * @param fid
     */
    void download(HttpServletResponse response, Integer fid);

    /**
     * 删除 文件
     * @param fidList
     */
    void deleteMultiple(List<Integer> fidList);

    /**
     * 删除某用户下所有文件
     * @param uid
     */
    void deleteMultiple(Integer uid);
}
