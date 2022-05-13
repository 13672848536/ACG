package com.example.xjlacgapp.mapper;

import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.pojo.Browse;
import com.example.xjlacgapp.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 添加订单
     * @param order
     * @return
     */
    Boolean addOrder(Order order);
    List<Order> queryById(Long userid);
    List<Order> queryById3(Long userid);
    List<Order> queryById2(Long id);
    List<Order> queryByProduct(Long productid);
    int update(Long id, String state);
    List<LookDTO> queryByTotal(Long productid);
    Order SumTotal(Long productid,Long userid);
    List<Order> findId();
}
