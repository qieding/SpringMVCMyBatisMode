package com.dhk.service.impl;

import com.dhk.dao.ItemsMapperDao;
import com.dhk.entity.Items;
import com.dhk.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsMapperDao itemsMapperDao;
    @Override
    public Items getItemsById(Integer id) {
        Items items = itemsMapperDao.selectItemsById(id);
        return items;
    }
}
