package com.example.xjlacgapp.service;


import com.example.xjlacgapp.model.BrowseDate;

import java.util.List;

public interface BrowseService {
    Boolean addOrder(String userid , String productid);

    List<BrowseDate> queryById(String userid);
}
