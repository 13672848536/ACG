package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Provincial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProvincialMapper {
    List<Provincial> queryAll();
    Provincial queryById(int pid);
}
