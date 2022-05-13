package com.example.xjlacgapp.service.impl;


import com.example.xjlacgapp.mapper.ProductMapper;
import com.example.xjlacgapp.mapper.ProductTypeMapper;
import com.example.xjlacgapp.pojo.Product;
import com.example.xjlacgapp.pojo.ProductType;
import com.example.xjlacgapp.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private ProductTypeMapper productTypeMapper;
    private ProductMapper productMapper;
    @Autowired
    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper,ProductMapper productMapper){
        this.productTypeMapper = productTypeMapper;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductType> queryById(String productid) {
        return productTypeMapper.queryById(Long.valueOf(productid));
    }

    @Override
    public int addProductType(ProductType productType) {
        Product product = productMapper.findByProductId(productType.getProductid());
        int stock= product.getStock()+productType.getStock();
        productMapper.updateStock2(stock,productType.getProductid());
        return productTypeMapper.addProductType(productType);
    }

    @Override
    public int updateStock(int stock, String productid, String type, String size) {
        return productTypeMapper.updateStock(stock, Long.valueOf(productid),type,size);
    }

    @Override
    public Boolean deleteByProductType(String productid, String type, String size) {
        Product product = productMapper.findByProductId(Long.valueOf(productid));
        ProductType productType = productTypeMapper.queryId(Long.valueOf(productid),type,size);
        int stock= product.getStock()-productType.getStock();
        productMapper.updateStock2(stock,Long.valueOf(productid));
        return productTypeMapper.deleteByProductType(Long.valueOf(productid),type,size);
    }

    @Override
    public List<String> findType(String productid) {
        List<ProductType> data = productTypeMapper.findType(Long.valueOf(productid));
        List<String> types= new ArrayList<String>();
        for(int i = 0; i < data.size();i++) {
            types.add(0, data.get(i).getType());
        }
        return types;

    }

    @Override
    public List<ProductType> findSize(String productid, String type) {
        List<ProductType> data = productTypeMapper.findSize(Long.valueOf(productid),type);
        return data;
    }
}
