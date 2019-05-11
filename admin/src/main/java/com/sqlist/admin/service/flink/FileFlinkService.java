package com.sqlist.admin.service.flink;

import java.io.File;
import java.io.IOException;

/**
 * @author SqList
 * @date 2019/5/5 22:32
 * @description
 **/
public interface FileFlinkService {

    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    String upload(File file) throws IOException;

    /**
     * 上传文件
     * @param filePath
     * @return
     * @throws IOException
     */
    String upload(String filePath) throws IOException;
}
