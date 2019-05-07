package com.sqlist.web.service;

import com.sqlist.web.domain.File;
import com.sqlist.web.domain.User;
import com.sqlist.web.vo.FileVO;
import com.sqlist.web.vo.PageVO;

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
}
