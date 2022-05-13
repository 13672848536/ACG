package com.example.xjlacgapp.service.impl;

import com.example.xjlacgapp.mapper.CityMapper;
import com.example.xjlacgapp.mapper.ProvincialMapper;
import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.Provincial;
import com.example.xjlacgapp.service.CityService;
import com.example.xjlacgapp.service.ProvincialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincialServiceImpl implements ProvincialService {
    private ProvincialMapper provincialMapper;
    @Autowired
    public ProvincialServiceImpl(ProvincialMapper provincialMapper){
        this.provincialMapper = provincialMapper;
    }

    @Override
    public List<Provincial> queryAll() {
        return provincialMapper.queryAll();
    }
}
