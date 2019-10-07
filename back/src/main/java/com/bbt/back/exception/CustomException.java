package com.bbt.back.exception;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 20:15
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}
