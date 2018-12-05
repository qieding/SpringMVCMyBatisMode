package com.dhk.entity;

import com.dhk.controller.validation.ValidGroup1;
import com.dhk.controller.validation.ValidGroup2;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class Items {
    private Integer id;
    @Size(min=1,max = 10,message = "{items.name.length.error}",groups = ValidGroup1.class)
    private String name;
    private Float price;
    @NotNull(message = "{items.createtime.isNull}",groups = ValidGroup2.class)
    private String detail;
    private String pic;
    private Date createtime;
}
