package com.sqlist.web.controller;

import com.sqlist.web.domain.Device;
import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.DeviceService;
import com.sqlist.web.service.ProductService;
import com.sqlist.web.vo.DeviceVO;
import com.sqlist.web.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/26 14:21
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, @Validated PageVO pageVO) {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setUid(user.getUid());
        return Result.success(deviceService.list(deviceVO, pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated(DeviceVO.Add.class) @RequestBody DeviceVO deviceVO) {
        log.info("add(), user: {}, deviceVO: {}", user, deviceVO);
        deviceService.add(user, deviceVO);

        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @RequestBody List<Integer> didList) {

        deviceService.deleteMultiple(user, didList);

        return Result.success(null);
    }

    @RequestMapping(value = "/{did}", method = RequestMethod.GET)
    public Result detail(User user, @PathVariable("did") Integer did) {
        Device device = new Device();
        device.setUid(user.getUid());
        device.setDid(did);
        device = deviceService.detail(device);

        Product product = new Product();
        product.setPid(device.getPid());
        product.setUid(user.getUid());
        product = productService.detail(product);

        Map<String, Object> map = new HashMap<>();
        map.put("device", device);
        map.put("product", product);

        return Result.success(map);
    }

//    @RequestMapping(value = "/noProduct", method = RequestMethod.GET)
//    public Result noProductDevice(User user, @Validated PageVO pageVO) {
//        DeviceVO deviceVO = new DeviceVO();
//        deviceVO.setUid(user.getUid());
//        deviceVO.setPid(0);
//        return Result.success(deviceService.list(deviceVO, pageVO));
//    }
}
