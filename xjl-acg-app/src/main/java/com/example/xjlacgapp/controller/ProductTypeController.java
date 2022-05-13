package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.ProductType;
import com.example.xjlacgapp.service.impl.CityServiceImpl;
import com.example.xjlacgapp.service.impl.ProductTypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市管理API
 *
 * @author wq
 * @version 1.0
 * @date 2021-09-05 11:55:58
 */
@Api(tags = "商品类型API")
@RestController
@RequestMapping("/api/demo/productType")
public class ProductTypeController {
    @Autowired
    ProductTypeServiceImpl productTypeService;

    @ApiOperation(value = "查看商品类型")
    @PostMapping("/queryById")
    List<ProductType> queryById(@RequestParam(value = "productid") String productid){
        return productTypeService.queryById(productid);
    }
    @ApiOperation(value = "添加")
    @PostMapping("/addProductType")
    int addProductType(@RequestBody ProductType productType){
        return productTypeService.addProductType(productType);
    }
    @ApiOperation(value = "删除")
    @PostMapping("/deleteByProductType")
   Boolean deleteByProductType(@RequestParam(value = "productid") String productid,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "size") String size){
        return productTypeService.deleteByProductType(productid,type,size);
    }

    @ApiOperation(value = "查询类型")
    @PostMapping("/findType")
    public List<String> findType( @RequestParam(value = "productid") String productid){
        return productTypeService.findType(productid);
    }
    @ApiOperation(value = "查询类型")
    @PostMapping("/findSize")
    public List<ProductType> findSize( @RequestParam(value = "productid") String productid,@RequestParam(value = "type") String type){
        return productTypeService.findSize(productid,type);
    }
}
