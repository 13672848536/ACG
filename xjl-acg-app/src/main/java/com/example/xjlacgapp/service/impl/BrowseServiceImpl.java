package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.BrowseMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.UserMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.User;
import com.example.xjlacgapp.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BrowseServiceImpl implements BrowseService {
    private BrowseMapper browseMapper;
    private ProductMapper productMapper;
    private UserMapper userMapper;
    @Autowired
    public BrowseServiceImpl(BrowseMapper browseMapper,ProductMapper productMapper,UserMapper userMapper){
        this.browseMapper = browseMapper;
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }
    @Override
    public Boolean addOrder(String userid, String productid) {
        Browse browse = new Browse();
        User  user = userMapper.findById(Long.valueOf(userid));
        Product product = productMapper.findByProductId(Long.valueOf(productid));
        browse.setType(product.getType());
        browse.setAge(user.getYear());
        browse.setUserid(Long.valueOf(userid));
        browse.setProductid(Long.valueOf(productid));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
           browse.setCreatetime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return browseMapper.addBrowse(browse);
    }

    @Override
    public List<BrowseDate> queryById(String userid) {
        List<Browse> list = browseMapper.queryById(Long.valueOf(userid));
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
            browseDates.add(0,browseDate);
        }
        return browseDates;
    }
}
