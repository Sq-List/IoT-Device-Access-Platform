package com.sqlist.web.service;

import com.sqlist.web.domain.File;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.FileVO;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.search.FileSearchVO;

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
     * @param fileSearchVO
     * @return
     */
    Map<String, Object> list(User user, FileSearchVO fileSearchVO);

    /**
     * 获取 某extensions 后缀的所有上传文件
     * @param user
     * @param extensions
     * @return
     */
    List<File> list(User user, String extensions);

    /**
     * 获取
     * @param fid
     * @return
     */
    File get(Integer fid);

    /**
     * 上传 文件
     * @param user
     * @param fileVO
     */
    void upload(User user, FileVO fileVO);

    /**
     * 下载文件
     * @param response
     * @param fid
     * @throws UnsupportedEncodingException
     */
    void download(HttpServletResponse response, Integer fid) throws UnsupportedEncodingException;

    /**
     * 删除 文件
     * @param user
     * @param fidList
     */
    void delete(User user, List<Integer> fidList);

    /**
     * 更新 jarId
     * @param fid
     * @param jarId
     */
    void updateJarId(Integer fid, String jarId);

    /**
     * 统计某用户的文件数
     * @param user
     * @return
     */
    Integer count(User user);
}
