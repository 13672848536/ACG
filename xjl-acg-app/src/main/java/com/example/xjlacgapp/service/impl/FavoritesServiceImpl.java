package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.FavoritesMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Favorites;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    private FavoritesMapper favoritesMapper;
    private ProductMapper productMapper;
    @Autowired
    public  FavoritesServiceImpl(FavoritesMapper favoritesMapper,ProductMapper productMapper){
        this.favoritesMapper = favoritesMapper;
        this.productMapper = productMapper;
    }
    @Override
    public Boolean addFavorites(Favorites favorites) {

        return favoritesMapper.addFavorites(favorites);
    }

    @Override
    public Boolean deleteByFavorites(Favorites favorites) {
        return favoritesMapper.deleteByFavorites(favorites);
    }

    @Override
    public Boolean findFavorites(Favorites favorites) {
        Favorites favorites1 = favoritesMapper.findFavorites(favorites);
        if (favorites1 != null){
            return  true;
        }
        return false;
    }

    @Override
    public List<BrowseDate> queryById(String userid) {
        List<Favorites> list = favoritesMapper.queryById(Long.valueOf(userid));
        List<BrowseDate> browseDates = new ArrayList<BrowseDate>();
        for (int i = 0; i < list.size();i++){
            Product product = productMapper.findByProductId(list.get(i).getProductid());
            BrowseDate browseDate = new BrowseDate();
            browseDate.setProductid(product.getProductid());
            browseDate.setPrice(product.getPrice());
            browseDate.setTitle(product.getTitle());
            browseDate.setImg(product.getImg());
            browseDate.setUsername(product.getUsername());
            browseDate.setAmount(product.getAmount());
            browseDates.add(0,browseDate);
        }
        return browseDates;
    }
}
