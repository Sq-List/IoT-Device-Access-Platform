package com.sqlist.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    public static void delete(String path, String fileFullName) {
        File file = new File(path, fileFullName);
        log.info("file path: {}", file.getPath());

        if (!file.exists()) {
            return;
        }

        log.debug("delete before, file exist: {}", file.exists());
        file.delete();
        log.debug("delete after, file exist: {}", file.exists());
    }
}
