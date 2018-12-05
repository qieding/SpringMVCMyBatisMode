package com.dhk.vo;

import com.dhk.entity.Orders;
import com.dhk.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserOrdersVo extends User {
    //一对多
    private List<Orders> ordersList;
}
