package com.example.xjlacgapp.controller;


import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Favorites;
import com.example.xjlacgapp.service.impl.FavoritesServiceImpl;
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
@RequestMapping("/api/demo/favorites")
public class FavoritesController {
    @Autowired
    FavoritesServiceImpl favoritesService;
    @ApiOperation(value = "收藏添加")
    @PostMapping("/addFavorites")

    public Boolean addFavorites(@RequestBody Favorites favorites){
        return favoritesService.addFavorites(favorites);
    }

    @ApiOperation(value = "收藏添加")
    @PostMapping("/deleteFavorites")

    public Boolean deleteByFavorites(@RequestBody Favorites favorites){
        return favoritesService.deleteByFavorites(favorites);
    }

    @ApiOperation(value = "收藏添加")
    @PostMapping("/findFavorites")

    public Boolean findFavorites(@RequestBody Favorites favorites){
        return favoritesService.findFavorites(favorites);
    }

    @ApiOperation(value = "查看浏览")
    @PostMapping("/queryById")
    List<BrowseDate> queryById(@RequestParam(value = "userid") String userid){
        return favoritesService.queryById(userid);
    }
}
