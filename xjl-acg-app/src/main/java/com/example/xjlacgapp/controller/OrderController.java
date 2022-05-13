package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.OrderDate;
import com.example.xjlacgapp.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "订单管理API")
@RestController
@RequestMapping("/api/demo/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @ApiOperation(value = "商品添加")
    @PostMapping("/addOrder")

    public Boolean addOrder(@RequestBody OrderDate orderDate){
        return orderService.addOrder(orderDate);
    }

    @ApiOperation(value = "查看浏览")
    @PostMapping("/queryById")
    List<BrowseDate> queryById(@RequestParam(value = "userid") String userid){
        return orderService.queryById(userid);
    }

    @ApiOperation(value = "查看订单")
    @PostMapping("/queryById2")
    List<BrowseDate> queryById2(@RequestParam(value = "id") String id){
        return orderService.queryById2(id);
    }
    @ApiOperation(value = "查看订单")
    @PostMapping("/queryByProduct")
    List<BrowseDate> queryByProduct(@RequestParam(value = "userid") String userid){
        return orderService.queryByProduct(userid);
    }
    @ApiOperation(value = "查看订单详情")
    @PostMapping("/queryById3")
    BrowseDate queryById3(@RequestParam(value = "id") String id){
        return  orderService.queryById3(id);
    }

    @ApiOperation(value = "更新状态")
    @PostMapping("/update")
    int update(@RequestParam(value = "id") String id,@RequestParam(value = "state") String state){
        return  orderService.update(id,state);
    }

    @ApiOperation(value = "统计订单")
    @PostMapping("/queryByTotal")
    List<LookDTO> queryByTotal(@RequestParam(value = "productid") String productid){
        return  orderService.queryByTotal(productid);
    }
    @ApiOperation(value = "统计商品订单各个年龄段的消费")
    @PostMapping("/SumTotal")
    List<String> SumTotal(@RequestParam(value = "productid") String productid){
        return  orderService.SumTotal(productid);
    }
}
