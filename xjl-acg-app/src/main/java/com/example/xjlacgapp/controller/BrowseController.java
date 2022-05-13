package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.model.OrderDate;
import com.example.xjlacgapp.service.impl.BrowseServiceImpl;
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
@Api(tags = "浏览记录管理API")
@RestController
@RequestMapping("/api/demo/browse")
public class BrowseController {
    @Autowired
    BrowseServiceImpl browseService;
    @ApiOperation(value = "浏览添加")
    @PostMapping("/addBrowse")

    public Boolean addBrowse(@RequestParam(value = "userid")String userid, @RequestParam(value = "productid")String productid){
        return browseService.addOrder(userid,productid);
    }

    @ApiOperation(value = "查看浏览")
    @PostMapping("/queryById")
    List<BrowseDate> queryById( @RequestParam(value = "userid") String userid){
        return browseService.queryById(userid);
    }
}
