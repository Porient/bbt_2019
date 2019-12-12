package com.bbt.back.model;

import com.bbt.back.entities.User;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/12 0012 12:33
 */
public class RegisterDto implements Serializable {
    private User user;
    private String verifyCode;

    public RegisterDto() {

    }

    public RegisterDto(User user, String verifyCode) {
        this.user = user;
        this.verifyCode = verifyCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
