package com.dhk.entity;

import lombok.Data;
@Data
public class Person {
    private Long id;
    private String name;
    private String email;
    private int status;

//    //getter  setter
//    @Override
//    public String toString(){
//        return this.getId()+"---"+this.getName()+"---"+
//                this.getEmail()+"---"+this.getStatus();
//    }
}
