package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.BrowseMapper;
import com.example.xjlacgapp.mapper.CityMapper;
import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.service.BrowseService;
import com.example.xjlacgapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityMapper cityMapper;
    @Autowired
    public CityServiceImpl(CityMapper cityMapper){
        this.cityMapper = cityMapper;
    }


    @Override
    public List<City> queryById(String pid) {
        return cityMapper.queryById(Integer.parseInt(pid));
    }
}
