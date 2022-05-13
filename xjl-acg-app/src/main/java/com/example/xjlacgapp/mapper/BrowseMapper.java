package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.pojo.Browse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BrowseMapper {
    /**
     * 添加订单
     * @param browse
     * @return
     */
    Boolean addBrowse(Browse browse);
    List<Browse> queryById(Long userid);
    Browse likeType();
    Browse likeType2();
    Browse likeType3();
}
