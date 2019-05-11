package com.sqlist.admin.controller;

import com.sqlist.admin.domain.Product;
import com.sqlist.admin.result.Result;
import com.sqlist.admin.service.DeviceService;
import com.sqlist.admin.service.ProductService;
import com.sqlist.admin.vo.DeviceVO;
import com.sqlist.admin.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SqList
 * @date 2019/5/11 23:16
 * @description
 **/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@Validated PageVO pageVO) {
        return Result.success(productService.list(null, pageVO));
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Result delete(@RequestBody List<Integer> pidList) {
        productService.deleteMultiple(pidList);
        return Result.success(null);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public Result detail(@PathVariable("pid") Integer pid) {
        Product product = new Product();
        product.setPid(pid);
        return Result.success(productService.detail(product));
    }

    @RequestMapping(value = "/{pid}/devices", method = RequestMethod.GET)
    public Result productDevices(@PathVariable("pid") Integer pid, PageVO pageVO) {
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setPid(pid);

        return Result.success(deviceService.list(deviceVO, pageVO));
    }
}
