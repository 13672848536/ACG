package com.example.xjlacgapp.service;


import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.pojo.City;

import java.util.List;

public interface CityService {

    List<City> queryById(String pid);
}
