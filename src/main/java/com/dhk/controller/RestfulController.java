package com.dhk.controller;

import com.dhk.entity.Items;
import com.dhk.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestfulController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping(value = "/items/{id}")
    @ResponseBody
    public Items itemsView(@PathVariable("id") Integer id)throws Exception{
        System.out.println("asfd::"+id);
        Items items = itemsService.getItemsById(id);
//        return items.toString();
        return items;
    }

}
