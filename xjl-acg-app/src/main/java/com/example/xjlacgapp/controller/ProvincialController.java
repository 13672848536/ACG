package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.Provincial;
import com.example.xjlacgapp.service.impl.CityServiceImpl;
import com.example.xjlacgapp.service.impl.ProvincialServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 城市管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "城市管理API")
@RestController
@RequestMapping("/api/demo/provincial")
public class ProvincialController {
    @Autowired
    ProvincialServiceImpl provincialService;

    @ApiOperation(value = "查看城市")
    @PostMapping("/queryAll")
    List<Provincial> queryAll(){
        return provincialService.queryAll();
    }
}
