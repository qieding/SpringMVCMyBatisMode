package com.dhk.service.impl;

import com.dhk.dao.UserMapperDao;
import com.dhk.entity.User;
import com.dhk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
//    @Resource(name="userMapper123")
    private UserMapperDao userMapperDao;
    public User getUserByUserId(Integer id){
        User user  = userMapperDao.selectUserById(id);
        return user;
    }
}
