package com.example.xjlacgapp.service;


import com.example.xjlacgapp.pojo.City;
import com.example.xjlacgapp.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> queryById(String productid);
    int addProductType(ProductType productType);
    int updateStock(int stock,String productid,String type,String size);
    Boolean deleteByProductType(String productid,String type,String size);
    /**
     * 查询类型
     * @return
     */
    List<String> findType(String productid);
    List<ProductType> findSize(String productid,String type);
}
