package com.example.xjlacgapp.mapper;


import com.example.xjlacgapp.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CityMapper {
    List<City> queryById(int pid);
}
