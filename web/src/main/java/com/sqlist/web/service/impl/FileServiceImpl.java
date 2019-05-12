package com.sqlist.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.web.domain.File;
import com.sqlist.web.domain.User;
import com.sqlist.web.exception.GlobalException;
import com.sqlist.web.mapper.FileMapper;
import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.service.flink.FileFlinkService;
import com.sqlist.web.service.FileService;
import com.sqlist.web.util.FileUtil;
import com.sqlist.web.util.ScpUtil;
import com.sqlist.web.vo.FileVO;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.search.FileSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
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

    @Value("${config.file.localPath}")
    private String localPath;

    @Value("${config.file.remotePath}")
    private String remotePath;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileFlinkService fileFlinkService;

    @Autowired
    private ScpUtil scpUtil;

    @Override
    public Map<String, Object> list(User user, FileSearchVO fileSearchVO) {
        File file = new File();
        file.setName(fileSearchVO.getName());
        file.setExtensions(fileSearchVO.getExtensions());
        file.setUid(user.getUid());

        HashMap<String, Object> map = new HashMap<>();

        if (fileSearchVO.getLimit() != -1) {
            PageHelper.startPage(fileSearchVO.getPage(), fileSearchVO.getLimit());
        }
        List<File> fileList = fileMapper.select(file);

        fileList.forEach((tmpFile) -> tmpFile.setPath(null));

        if (fileSearchVO.getLimit() != -1) {
            map.put("total", ((Page)fileList).getTotal());
        }
        map.put("list", fileList);

        return map;
    }

    @Override
    public List<File> list(User user, String extensions) {
        File file = new File();
        file.setUid(user.getUid());
        file.setExtensions(extensions);
        return fileMapper.select(file);
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
        String originalName = file.getOriginalFilename();
        String extensions = originalName.substring(originalName.lastIndexOf(".") + 1);
        String savePath = localPath + java.io.File.separator + user.getUsername() + java.io.File.separator + extensions;
        String fileName = fileVO.getName() + "." + extensions;
        String localFileFullPath = new java.io.File(savePath, fileName).getPath();

        File saveFile = new File();
        saveFile.setName(fileVO.getName());
        saveFile.setUid(user.getUid());

        if (fileMapper.selectCount(saveFile) != 0) {
            throw new GlobalException(CodeMsg.FILE_NAME_REPEAT);
        }

        String jarId = null;
        try {
            FileUtil.save(localFileFullPath, fileVO.getFile());
            if (extensions.equals(File.JAR)) {
                jarId = fileFlinkService.upload(localFileFullPath);
            } else if (extensions.equals(File.LUA)){
                jarId = remotePath + "/" + user.getUsername() + "-" + fileName;
                scpUtil.remoteSave(localFileFullPath, jarId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.UPLOAD_FILE_IO_EXCEPTION);
        }

        saveFile.setMainClass(fileVO.getMainClass());
        saveFile.setImplementClass(fileVO.getImplementClass());
        saveFile.setExtensions(extensions);
        saveFile.setPath(localFileFullPath);
        saveFile.setJarId(jarId);
        saveFile.setUploadTime(new Date());
        fileMapper.insert(saveFile);
    }

    @Override
    public void download(HttpServletResponse response, Integer fid) throws UnsupportedEncodingException {
        File file = get(fid);

        String fileName = file.getName() + "." + file.getExtensions();
        String path = file.getPath();
        java.io.File localFile = new java.io.File(path);
        if (!localFile.exists()) {
            log.error("下载文件不存在");
            throw new GlobalException(CodeMsg.FILE_NOT_EXIST);
        }
        log.info("文件存在");

        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(localFile);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            log.info("传输完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
            FileUtil.delete(deletePath);

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
