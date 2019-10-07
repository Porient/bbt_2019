package com.bbt.back.exception;

/**
 * @Description: 注册逻辑异常类
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 15:09
 */
public class RegisterException extends RuntimeException{
    private Integer code;

    public RegisterException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}