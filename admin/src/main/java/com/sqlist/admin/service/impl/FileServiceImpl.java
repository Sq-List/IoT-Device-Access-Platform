package com.sqlist.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.admin.domain.File;
import com.sqlist.admin.domain.User;
import com.sqlist.admin.exception.GlobalException;
import com.sqlist.admin.mapper.FileMapper;
import com.sqlist.admin.result.CodeMsg;
import com.sqlist.admin.service.FileService;
import com.sqlist.admin.util.FileUtil;
import com.sqlist.admin.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/28 2:05
 * @description
 **/
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        File file = new File();
        if (user != null) {
            file.setUid(user.getUid());
        }

        HashMap<String, Object> map = new HashMap<>();

        if (pageVO.getLimit() != -1) {
            PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        }
        List<File> fileList = fileMapper.select(file);

        fileList.forEach((tmpFile) -> tmpFile.setPath(null));

        if (pageVO.getLimit() != -1) {
            map.put("total", ((Page)fileList).getTotal());
        }
        map.put("list", fileList);

        return map;
    }

    @Override
    public File get(Integer fid) {
        File file = new File();
        file.setFid(fid);
        return fileMapper.selectByPrimaryKey(file);
    }

    @Override
    public void download(HttpServletResponse response, Integer fid) {
        File file = get(fid);

        String fileName = file.getName() + "." + file.getExtensions();
        String path = file.getPath();
        java.io.File localFile = new java.io.File(path);
        if (!localFile.exists()) {
            log.error("下载文件不存在");
            throw new GlobalException(CodeMsg.FILE_DOWNLOAD_FAIL);
        }
        log.info("文件存在");

        try {
            FileUtil.download(response, fileName, localFile);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.FILE_DOWNLOAD_FAIL);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(List<Integer> fidList) {
        fidList.forEach((fid) -> {
            File file = new File();
            file.setFid(fid);
            file = fileMapper.selectByPrimaryKey(file);

            String deletePath = file.getPath();
            FileUtil.delete(deletePath);

            fileMapper.deleteByPrimaryKey(file);
        });
    }
}
