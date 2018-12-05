package com.dhk.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该转换器将输入的日期字符串格式转换为Date类型，要想处理器映射器注入该Converter；
 * 转换器属于Controller输入参数的部分。
 */
public class CustomDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行转换器……");
        try{
            return simpleDateFormat.parse(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
