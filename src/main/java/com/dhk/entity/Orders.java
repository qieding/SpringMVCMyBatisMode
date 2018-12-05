package com.dhk.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -2083264337165290152L;
    private Integer id;
    private Integer userId;
    private String number;
    private Timestamp createTime;
    private String note;

}
