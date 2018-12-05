package com.dhk.vo;

import com.dhk.entity.Orders;
import com.dhk.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 每个订单只有一个用户
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrdersUserVo extends Orders {
    private User user;
}
