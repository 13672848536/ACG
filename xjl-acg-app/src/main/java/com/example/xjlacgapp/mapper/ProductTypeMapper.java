package com.example.xjlacgapp.mapper;


import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.ProductType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProductTypeMapper {
    List<ProductType> queryById(Long productid);
    int addProductType(ProductType productType);
    int updateStock(int stock,Long productid,String type,String size);
    Boolean deleteByProductType(Long productid,String type,String size);
    ProductType queryId(Long productid,String type,String size);
    List<ProductType> findType(Long productid);
    List<ProductType> findSize(Long productid,String type);
}
