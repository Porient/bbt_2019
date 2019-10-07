package com.bbt.back.exception;

/**
 * @Description: 封装系统异常类
 * @Author: Liu Bin
 * @Date: 2019/10/7 15:13
 */
public class SystemException extends RuntimeException{
    private Integer code;

    public SystemException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
