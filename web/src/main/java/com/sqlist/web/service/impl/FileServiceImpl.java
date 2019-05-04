package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.domain.File;
import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.FileMapper;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.FileService;
import com.sqlist.web.util.FileUnit;
import com.sqlist.web.vo.FileVO;
import com.sqlist.web.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/28 2:05
 * @description
 **/
@Service
public class FileServiceImpl implements FileService {

    @Value("${config.file.path}")
    private String path;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        File file = new File();
        file.setUid(user.getUid());

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

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void upload(User user, FileVO fileVO) {
        MultipartFile file = fileVO.getFile();
        String savePath = path + java.io.File.separator + user.getUsername();
        String originalName = file.getOriginalFilename();
        String extensions = originalName.substring(originalName.lastIndexOf(".") + 1);
        String fileName = fileVO.getName() + "." + extensions;

        File saveFile = new File();
        saveFile.setName(fileVO.getName());
        saveFile.setUid(user.getUid());

        if (fileMapper.selectCount(saveFile) != 0) {
            throw new GlobalException(CodeMsg.FILE_NAME_REPEAT);
        }

        try {
            FileUnit.save(savePath, fileName, fileVO.getFile());
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.UPLOAD_FILE_IO_EXCEPTION);
        }

        saveFile.setMainClass(fileVO.getMainClass());
        saveFile.setExtensions(extensions);
        saveFile.setPath(new java.io.File(savePath, fileName).getPath());
        saveFile.setUploadTime(new Date());
        fileMapper.insert(saveFile);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(User user, List<Integer> fidList) {
        fidList.forEach((fid) -> {
            File file = new File();
            file.setFid(fid);
            file.setUid(user.getUid());
            file = fileMapper.selectByPrimaryKey(file);

            String deletePath = file.getPath();
            String fileName = file.getName() + "." + file.getExtensions();
            FileUnit.delete(deletePath, fileName);

            fileMapper.deleteByPrimaryKey(file);
        });
    }

    @Override
    public void updateJarId(Integer fid, String jarId) {
        File file = new File();
        file.setFid(fid);
        file.setJarId(jarId);

        fileMapper.updateByPrimaryKeySelective(file);
    }
}
