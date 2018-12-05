package com.dhk.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable{
    private static final long serialVersionUID = 4094190605086606307L;
    private Integer id;
    private String userName;
    private Timestamp birthday;
    private String sex;
    private String address;
}
