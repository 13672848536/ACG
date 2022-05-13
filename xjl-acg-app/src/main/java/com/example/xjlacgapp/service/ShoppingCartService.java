package com.example.xjlacgapp.service;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ShoppingCartService {
    int addShoppingCart(ShoppingCart shoppingCart);
    List<BrowseDate> queryById(String userid);
    Boolean deleteByShoppingCart(String id);
    void doPost (HttpServletRequest req, HttpServletResponse resp, ShoppingCart shoppingCart) throws ServletException, IOException;
    List<BrowseDate> getAttribute (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void deleteCat (HttpServletRequest req, HttpServletResponse resp ,ShoppingCart shoppingCart) throws ServletException, IOException;
}
