package com.dhk.dao;

import com.dhk.entity.User;
import com.dhk.vo.UserOrdersVo;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.springframework.stereotype.Repository;

//@Repository 注解在这里可以没有因为XMl中配置了会扫描该包下的Dao去注入实例。
@CacheNamespaceRef(UserMapperDao.class)
public interface UserMapperDao {
    User selectUserById(Integer id);
    UserOrdersVo selectUserOrdersByUserId(Integer id);
    UserOrdersVo selectUserOrdersByUserId2(Integer id);
    User selectUserByUser(User u);
}
