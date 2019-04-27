package com.sqlist.web.controller;

import com.sqlist.web.domain.Device;
import com.sqlist.web.domain.Product;
import com.sqlist.web.domain.User;
import com.sqlist.web.result.Result;
import com.sqlist.web.service.DeviceService;
import com.sqlist.web.service.ProductService;
import com.sqlist.web.vo.DeviceVO;
import com.sqlist.web.vo.PageVO;
import com.sqlist.web.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SqList
 * @date 2019/4/25 10:32
 * @description
 **/
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(User user, @Validated PageVO pageVO) {
        Map<String, Object> map = productService.list(user, pageVO);
        return Result.success(map);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public Result detail(User user, @PathVariable("pid") Integer pid) {
        Product product = new Product();
        product.setPid(pid);
        product.setUid(user.getUid());
        return Result.success(productService.detail(product));
    }

    @RequestMapping(value = "/{pid}/devices", method = RequestMethod.GET)
    public Result productDevices(User user, @PathVariable("pid") Integer pid, PageVO pageVO) {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setPid(pid);
        deviceVO.setUid(user.getUid());

        return Result.success(deviceService.list(deviceVO, pageVO));
    }

    /**
     * 从product中删除device时，pid为0
     * @param user
     * @param pid
     * @param didList
     * @return
     */
    @RequestMapping(value = "/{pid}/devices", method = {RequestMethod.PUT, RequestMethod.DELETE})
    public Result addDevices(User user, @PathVariable("pid") Integer pid, @RequestBody List<Integer> didList) {
        Device device = new Device();
        device.setUid(user.getUid());
        device.setPid(pid);

        deviceService.updatePidMultipleByDid(device, didList);

        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result add(User user, @Validated @RequestBody ProductVO productVO) {
        log.info("add(), user: {}, productVO: {}", user, productVO);

        productService.add(user, productVO);

        return Result.success(null);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(User user, @RequestBody List<Integer> pidList) {
        productService.deleteMultiple(user, pidList);
        return Result.success(null);
    }
}
