package com.dhk.dao;

import com.dhk.entity.Orders;
import com.dhk.vo.OrdersUserVo;

public interface OrdersMapperDao {
    public Orders selectOrdersById(Integer id);
    public OrdersUserVo selectOrdersUserByOrdersId(Integer orderId);
    public OrdersUserVo selectOrdersUserByOrdersId2(Integer orderId);
    public OrdersUserVo selectOrdersUserByOrdersId3(Integer orderId);
    public OrdersUserVo selectOrdersUserByOrdersId4(Integer orderId);
    public OrdersUserVo selectOrdersUserByOrdersId5(Integer orderId);
    public OrdersUserVo selectOrdersUserByOrdersId6(Integer orderId);
}
