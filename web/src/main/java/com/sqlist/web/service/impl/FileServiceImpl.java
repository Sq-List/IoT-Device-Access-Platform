package com.sqlist.web.service.impl;

import com.sqlist.web.domain.User;
import com.sqlist.web.service.FileService;
import com.sqlist.web.vo.PageVO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/28 2:05
 * @description
 **/
@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, Object> list(User user, PageVO pageVO) {
        return null;
    }
}
