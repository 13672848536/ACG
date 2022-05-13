package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.OrderMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.ProductTypeMapper;
import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.OrderDate;
import com.example.xjlacgapp.pojo.*;
import com.example.xjlacgapp.service.OrderService;
import com.example.xjlacgapp.unit.IdUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private  final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final ProductTypeMapper productTypeMapper;
    private  final UserMapper userMapper;
    @Autowired
    public OrderServiceImpl(ProductMapper productMapper, OrderMapper orderMapper, ProductTypeMapper productTypeMapper,UserMapper userMapper){
        this.productMapper = productMapper;
        this. orderMapper= orderMapper;
        this.productTypeMapper = productTypeMapper;
        this.userMapper = userMapper;
    }
    @Override
    public Boolean addOrder(OrderDate orderDate) {
        Order order = new Order();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        order.setId(Long.valueOf(IdUnit.getId()));
        order.setUserid(Long.valueOf(orderDate.getUserid()));
        order.setProductid(Long.valueOf(orderDate.getProductid()));
        order.setState("未发货");
        order.setMonth(3);
        try {
            order.setCreatetime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setTotal(orderDate.getTotal());
        order.setType(orderDate.getType());
        order.setSize(orderDate.getSize());
        Product product = productMapper.findByProductId(Long.valueOf(orderDate.getProductid()));//查看商品信息
        if(product.getState().equals("0")){
        order.setStyle(product.getType());
        ProductType productType = productTypeMapper.queryId(Long.valueOf(orderDate.getProductid()),orderDate.getType(),orderDate.getSize());//查询商品子类型信息
        int stock= product.getStock()-orderDate.getTotal();
        int amount = product.getAmount()+orderDate.getTotal();
        int stock2=productType.getStock()-orderDate.getTotal();
        if(product.getStock() == 0){
            productMapper.deleteByProduct(Long.valueOf(orderDate.getProductid()));//下架商品
        }
        if (stock2 >= 0){
            productTypeMapper.updateStock(stock2,Long.valueOf(orderDate.getProductid()),orderDate.getType(),orderDate.getSize());//更新商品子类型库存
            productMapper.updateStock(stock,amount,Long.valueOf(orderDate.getProductid()));//更新商品库存
            orderMapper.addOrder(order);
            return true;
        }}
        return false;
    }

    @Override
    public List<BrowseDate> queryById(String userid) {
        List<Order> list = orderMapper.queryById(Long.valueOf(userid));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            BrowseDate browseDate = new BrowseDate();
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setProductid(product.getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setImg(product.getImg());
            browseDate.setAmount(product.getAmount());
            browseDate.setUsername(product.getUsername());
            browseDate.setId(list.get(i).getId());
            browseDate.setNum(list.get(i).getTotal());
            browseDate.setState(list.get(i).getState());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
            browseDates.add(0,browseDate);
        }
        return browseDates;
    }

    @Override
    public List<BrowseDate> queryById2(String id) {
        List<Order> list = orderMapper.queryById2(Long.valueOf(id));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            BrowseDate browseDate = new BrowseDate();
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setProductid(product.getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setImg(product.getImg());
            browseDate.setAmount(product.getAmount());
            browseDate.setUsername(product.getUsername());
            browseDate.setId(list.get(i).getId());
            browseDate.setNum(list.get(i).getTotal());
            browseDate.setState(list.get(i).getState());
            browseDate.setProduct(product.getProduct());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
            browseDates.add(0,browseDate);
        }
        return browseDates;
    }

    @Override
    public List<BrowseDate> queryByProduct(String userid) {
       List<Product> products = productMapper.findById(Long.valueOf(userid));
       List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
       for (int j = 0; j<products.size();j++){
        List<Order> list = orderMapper.queryByProduct(Long.valueOf(products.get(j).getProductid()));
        if(list.size() > 0){
        Product product = productMapper.findByProductId(list.get(0).getProductid());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size();i++){
            BrowseDate browseDate = new BrowseDate();
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setPrice(product.getPrice());
            browseDate.setId(list.get(i).getId());
            browseDate.setNum(list.get(i).getTotal());
            browseDate.setState(list.get(i).getState());
            browseDate.setProduct(product.getProduct());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
            browseDates.add(0,browseDate);
        }}}
        return browseDates;
    }

    @Override
    public BrowseDate queryById3(String id) {
        List<Order> list = orderMapper.queryById2(Long.valueOf(id));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BrowseDate browseDate = new BrowseDate();
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            browseDate.setCreatetime(formatter.format(list.get(i).getCreatetime()));
            browseDate.setProductid(list.get(i).getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setId(list.get(i).getUserid());
            browseDate.setNum(list.get(i).getTotal());
            browseDate.setState(list.get(i).getState());
            browseDate.setProduct(product.getProduct());
            browseDate.setType(list.get(i).getType());
            browseDate.setSize(list.get(i).getSize());
        }
        return browseDate;
    }

    @Override
    public int update(String id, String state) {
        return orderMapper.update(Long.valueOf(id),state);
    }

    @Override
    public List<LookDTO> queryByTotal(String productid) {
        return orderMapper.queryByTotal(Long.valueOf(productid));
    }

    @Override
    public List<String> SumTotal(String productid) {
        List<String> total = new ArrayList<String>();
        List<User> user1 = userMapper.queryAge1();
        List<User> user2 = userMapper.queryAge2();
        List<User> user3 = userMapper.queryAge3();
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i<user1.size();i++){
            Order order = orderMapper.SumTotal(Long.valueOf(productid),user1.get(i).getId());
            if(order != null){
            a+=order.getTotal();}}
        for (int i = 0; i<user2.size();i++){
            Order order = orderMapper.SumTotal(Long.valueOf(productid),user2.get(i).getId());
            if (order !=null){
            b+=order.getTotal();}
        }
        for (int i = 0; i<user3.size();i++){
            Order order = orderMapper.SumTotal(Long.valueOf(productid),user3.get(i).getId());
            if (order != null){
                c+=order.getTotal();
            }

        }
        total.add(0, String.valueOf(a));
        total.add(0, String.valueOf(b));
        total.add(0, String.valueOf(c));
        return total;
    }
}
