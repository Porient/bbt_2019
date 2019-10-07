package com.bbt.back.exception;

/**
 * @Description: 登录异常处理
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 15:10
 */
public class LoginException extends RuntimeException {
    private Integer code;

    public LoginException() {
    }

    public LoginException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}