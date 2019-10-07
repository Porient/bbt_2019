package com.bbt.back.exception;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 16:44
 */
public class UserException  extends RuntimeException{
    private Integer code;

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
