package com.example.xjlacgapp.controller;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.ShoppingCart;
import com.example.xjlacgapp.service.impl.ShoppingCartServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
@RequestMapping("/api/demo/ShoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @ApiOperation(value = "添加购物车")
    @PostMapping("/addShoppingCart")
    public int addShoppingCart( @RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addShoppingCart(shoppingCart);
    }

    @ApiOperation(value = "查询购物车")
    @PostMapping("/queryById")
    List<BrowseDate> queryById(@RequestParam(value = "userid") String userid){
        return shoppingCartService.queryById(userid);
    }
    @ApiOperation(value = "删除购物车")
    @PostMapping("/deleteId")
    void deleteByShoppingCart(HttpServletRequest req, HttpServletResponse resp,@RequestBody ShoppingCart shoppingCart){
//        return  shoppingCartService.deleteByShoppingCart(id);
        try {
            shoppingCartService.deleteCat(req,resp,shoppingCart);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "添加购物车")
    @PostMapping("/addCat")
   void addCart(HttpServletRequest req, HttpServletResponse resp,@RequestBody ShoppingCart shoppingCart){
        try {
            shoppingCartService.doPost (req,resp, shoppingCart);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "展示购物车")
    @PostMapping("/showCat")
    List<BrowseDate> showCart(HttpServletRequest req, HttpServletResponse resp) {
        try {
            return shoppingCartService.getAttribute(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
    @ApiOperation(value = "关闭会话")
    @PostMapping("/deleteSession")
    void deleteSession(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
    }
}
