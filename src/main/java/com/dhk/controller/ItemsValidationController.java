package com.dhk.controller;

import com.dhk.controller.validation.ValidGroup1;
import com.dhk.entity.Items;
import com.dhk.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemsValidationController {
    @Autowired
    ItemsService itemsService;

    @RequestMapping(value = "/getItemsById",method = {RequestMethod.GET},params = {"id!=5"})
    @ResponseBody
    public String getItemsById(@RequestParam(value = "id") Integer id,
                               @Validated(value ={ValidGroup1.class}) Items itemsValidated,
                               BindingResult bindingResult)throws Exception{
        //在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult bindingResult接收校验出错信息
        //注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
        Items items = itemsService.getItemsById(id);
        if(bindingResult.hasErrors()){
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            for (ObjectError objectError:objectErrors){
                System.out.println(objectError.getDefaultMessage());
            }
        }
        return "success";
    }
}
