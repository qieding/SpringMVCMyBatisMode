package com.dhk.exception;

import lombok.Data;

/**
 * 对不同的异常类型定义异常类
 */
@Data
public class CustomExceptionBean extends Exception {
    public String message;
    public CustomExceptionBean(String message) {
        super(message);
        this.message = message;
    }
}
