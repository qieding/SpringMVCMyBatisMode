package com.dhk.service.impl;

import com.dhk.service.PersonService;
import com.dhk.dao.PersonMapperDao;
import com.dhk.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
//    @Resource(name = "personMapperDao")
    private PersonMapperDao personMapperDao;
    public Person findPersonById(long id) {
        return personMapperDao.findPersonById(id);
    }
}
