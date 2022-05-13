package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.ShoppingCartMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.ShoppingCart;
import com.example.xjlacgapp.service.ShoppingCartService;
import com.example.xjlacgapp.unit.IdUnit;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartMapper shoppingCartMapper;
    private ProductMapper productMapper;
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper, ProductMapper productMapper){
        this.shoppingCartMapper = shoppingCartMapper;
        this.productMapper = productMapper;
    }

    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(Long.valueOf(IdUnit.getId()));
        return shoppingCartMapper.addShoppingCart(shoppingCart);
    }

    @Override
    public List<BrowseDate> queryById(String userid) {
        List<ShoppingCart> list = shoppingCartMapper.queryById(Long.valueOf(userid));
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            BrowseDate browseDate = new BrowseDate();
            browseDate.setProductid(product.getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setImg(product.getImg());
            browseDate.setAmount(product.getAmount());
            browseDate.setUsername(product.getUsername());
            browseDate.setNum(list.get(i).getAmount());
            browseDate.setId(list.get(i).getId());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
            browseDates.add(0,browseDate);
        }
        return browseDates;
    }

    @Override
    public Boolean deleteByShoppingCart(String id) {
        return shoppingCartMapper.deleteByShoppingCart(Long.valueOf(id));
    }

    public void doPost (HttpServletRequest req, HttpServletResponse resp, ShoppingCart shoppingCart) throws ServletException, IOException {
        HttpSession session=req.getSession();
        shoppingCart.setId(Long.valueOf(IdUnit.getId()));
        List<ShoppingCart> list=(List<ShoppingCart>) session.getAttribute("car");
        if(list==null) {
            list = new ArrayList<ShoppingCart>();
        }
        list.add(shoppingCart);
        session.setAttribute("car", list);
    }
    /**
     * 获取 Session 域中的数据
     */
    public List<BrowseDate> getAttribute (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        List<ShoppingCart> list=(List<ShoppingCart>) session.getAttribute("car");
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        if (list != null){
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            BrowseDate browseDate = new BrowseDate();
            browseDate.setProductid(product.getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setImg(product.getImg());
            browseDate.setAmount(product.getAmount());
            browseDate.setUsername(product.getUsername());
            browseDate.setNum(list.get(i).getAmount());
            browseDate.setId(list.get(i).getId());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
            browseDates.add(0,browseDate);
        }
        }
        return browseDates;
    }
    public void deleteCat (HttpServletRequest req, HttpServletResponse resp ,ShoppingCart shoppingCart) throws ServletException, IOException {
        HttpSession session=req.getSession();
        List<ShoppingCart> list=(List<ShoppingCart>) session.getAttribute("car");
        list.remove(shoppingCart);
        session.setAttribute("car", list);
    }

}
