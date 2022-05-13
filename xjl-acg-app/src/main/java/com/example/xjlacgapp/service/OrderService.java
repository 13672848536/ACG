package com.example.xjlacgapp.service;

import com.example.xjlacgapp.model.BrowseDate;
import com.example.xjlacgapp.model.LookDTO;
import com.example.xjlacgapp.model.OrderDate;
import com.example.xjlacgapp.pojo.Order;

import java.util.List;

public interface OrderService {
    /**
     * 添加订单
     * @param orderDate
     * @return
     */
    Boolean addOrder(OrderDate orderDate);

    /**
     * 根据用户id查订单数据
     * @param userid
     * @return
     */
    List<BrowseDate> queryById(String userid);

    /**
     * 根据订单id查数据
     * @param id
     * @return
     */
    List<BrowseDate> queryById2(String id);

    /**
     * 店家查订单数据
     * @param userid
     * @return
     */
    List<BrowseDate> queryByProduct(String userid);

    /**
     * 店家订单数据详情
     * @param id
     * @return
     */
    BrowseDate queryById3(String id);

    /**
     * 更新状态
     * @param id
     * @param state
     * @return
     */
    int update(String id, String state);

    List<LookDTO> queryByTotal(String productid);

    List<String> SumTotal(String productid);
}
