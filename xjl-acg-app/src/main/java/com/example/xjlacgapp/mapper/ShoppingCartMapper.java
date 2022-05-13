package com.example.xjlacgapp.mapper;


import com.example.xjlacgapp.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCartMapper {
    int addShoppingCart(ShoppingCart shoppingCart);
    List<ShoppingCart> queryById(Long userid);
    /**
     * 删除收藏
     * @param id
     * @return
     */
    Boolean deleteByShoppingCart(Long id);
}
