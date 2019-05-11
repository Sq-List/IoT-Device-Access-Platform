package com.sqlist.admin.controller;

import com.sqlist.admin.domain.User;
import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.service.ProductService;
import com.sqlist.admin.service.UserService;
import com.sqlist.admin.service.task.TaskService;
import com.sqlist.admin.vo.DeviceVO;
import com.sqlist.admin.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SqList
 * @date 2019/5/11 19:55
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        return Result.success(userService.list(pageVO));
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public Result get(@PathVariable("uid") Integer uid) {
        return Result.success(userService.get(uid));
    }

    @RequestMapping(value = "/{uid}/products", method = RequestMethod.GET)
    public Result products(@PathVariable("uid") Integer uid, @Validated PageVO pageVO) {
        User user = new User();
        user.setUid(uid);
        return Result.success(productService.list(user, pageVO));
    }

    @RequestMapping(value = "/{uid}/devices", method = RequestMethod.GET)
    public Result devices(@PathVariable("uid") Integer uid, @Validated PageVO pageVO) {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setUid(uid);
        return Result.success(deviceService.list(deviceVO, pageVO));
    }

    @RequestMapping(value = "/{uid}/tasks", method = RequestMethod.GET)
    public Result tasks(@PathVariable("uid") Integer uid, @Validated PageVO pageVO) {
        User user = new User();
        user.setUid(uid);
        return Result.success(taskService.list(user, pageVO));
    }
}
