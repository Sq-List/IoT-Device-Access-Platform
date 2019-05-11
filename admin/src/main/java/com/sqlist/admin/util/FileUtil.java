package com.sqlist.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author SqList
 * @date 2019/4/28 14:03
 * @description
 **/
@Slf4j
public class FileUtil {

    public static void save(String path, MultipartFile file) throws IOException {
        File filePath = new File(path);
        if (!filePath.getParentFile().exists()) {
            log.debug("create path: {}", filePath.getParentFile().getPath());
            filePath.getParentFile().mkdirs();
        }

        log.info("file save to {}", filePath.getPath());
        file.transferTo(filePath);
        log.debug("save after, file exist: {}", filePath.exists());
        log.info("file save success");
    }

    public static void delete(String path) {
        File file = new File(path);
        log.info("file path: {}", file.getPath());

        if (!file.exists()) {
            return;
        }

        log.debug("delete before, file exist: {}", file.exists());
        file.delete();
        log.debug("delete after, file exist: {}", file.exists());
    }

    public static void download(HttpServletResponse response, String fileName, File file) throws UnsupportedEncodingException {
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
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
}
