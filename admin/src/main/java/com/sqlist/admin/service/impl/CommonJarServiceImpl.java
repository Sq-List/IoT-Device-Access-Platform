package com.sqlist.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sqlist.admin.domain.CommonJar;
import com.sqlist.admin.exception.GlobalException;
import com.sqlist.admin.mapper.CommonJarMapper;
import com.sqlist.admin.result.CodeMsg;
import com.sqlist.admin.service.CommonJarService;
import com.sqlist.admin.service.flink.FileFlinkService;
import com.sqlist.admin.util.FileUtil;
import com.sqlist.admin.vo.CommonJarVO;
import com.sqlist.admin.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/2 2:22
 * @description
 **/
@Slf4j
@Service
public class CommonJarServiceImpl implements CommonJarService {

    public static final String COMMON = "common";

    @Value("${config.file.localPath}")
    private String localPath;

    @Autowired
    private CommonJarMapper commonJarMapper;

    @Autowired
    private FileFlinkService fileFlinkService;

    @Override
    public Map<String, Object> list(PageVO pageVO) {
        HashMap<String, Object> map = new HashMap<>();

        if (pageVO.getLimit() != -1) {
            PageHelper.startPage(pageVO.getPage(), pageVO.getLimit());
        }
        List<CommonJar> commonJarList = commonJarMapper.selectAll();

        if (pageVO.getLimit() != -1) {
            map.put("total", ((Page)commonJarList).getTotal());
        }
        map.put("list", commonJarList);

        return map;
    }

    @Override
    public void upload(CommonJarVO commonJarVO) {
        MultipartFile file = commonJarVO.getFile();
        String savePath = localPath + java.io.File.separator + COMMON + java.io.File.separator + commonJarVO.getTaskUnitType();
        String fileName = commonJarVO.getName();
        String localFileFullPath = new java.io.File(savePath, fileName).getPath();

        CommonJar commonJar = new CommonJar();
        commonJar.setTaskUnitType(commonJarVO.getTaskUnitType());
        commonJar.setType(commonJarVO.getType());

        if (commonJarMapper.selectCount(commonJar) != 0) {
            throw new GlobalException(CodeMsg.COMMON_JAR_TYPE_REPEAT);
        }

        String jarId = null;
        try {
            FileUtil.save(localFileFullPath, file);
            jarId = fileFlinkService.upload(localFileFullPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.UPLOAD_FILE_IO_EXCEPTION);
        }

        commonJar.setName(fileName);
        commonJar.setJarId(jarId);
        commonJar.setCreateTime(new Date());
        commonJar.setPath(localFileFullPath);
        commonJar.setMainClass(commonJarVO.getMainClass());
        commonJarMapper.insert(commonJar);
    }

    @Override
    public void download(HttpServletResponse response, Integer cjid) {
        CommonJar commonJar = new CommonJar();
        commonJar.setCjid(cjid);
        commonJar = commonJarMapper.selectByPrimaryKey(commonJar);

        String fileName = commonJar.getName();
        String path = commonJar.getPath();
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

    @Override
    public void delete(List<Integer> cjidList) {
        cjidList.forEach((cjid) -> {
            CommonJar commonJar = new CommonJar();
            commonJar.setCjid(cjid);
            commonJar = commonJarMapper.selectByPrimaryKey(commonJar);

            FileUtil.delete(commonJar.getPath());

            commonJarMapper.delete(commonJar);
        });
    }
}
