package com.dhk.dao;

import com.dhk.entity.Items;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsMapperDao {
    public Items selectItemsById(Integer id);
}
