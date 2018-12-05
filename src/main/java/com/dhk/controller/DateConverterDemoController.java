package com.dhk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class DateConverterDemoController {

    @RequestMapping(value = "/dateConverter")
    @ResponseBody
    public String dateConverter(@RequestParam(value = "dateString") Date date){
        System.out.println(date);
        return "success";
    }
}
