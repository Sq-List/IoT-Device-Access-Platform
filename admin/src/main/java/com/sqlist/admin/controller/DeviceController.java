package com.sqlist.admin.controller;

import com.sqlist.admin.domain.Device;
import com.sqlist.admin.domain.Product;
import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.service.ProductService;
import com.sqlist.admin.vo.DeviceVO;
import com.sqlist.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/5/12 0:49
 * @description
 **/
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        return Result.success(deviceService.list(new DeviceVO(), pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(@RequestBody List<Integer> didList) {

        deviceService.deleteMultiple(didList);

        return Result.success(null);
    }

    @RequestMapping(value = "/{did}", method = RequestMethod.GET)
    public Result detail(@PathVariable("did") Integer did) {
        Device device = new Device();
        device.setDid(did);
        device = deviceService.detail(device);

        Product product = new Product();
        product.setPid(device.getPid());
        product = productService.detail(product);

        Map<String, Object> map = new HashMap<>();
        map.put("device", device);
        map.put("product", product);

        return Result.success(map);
    }
}
