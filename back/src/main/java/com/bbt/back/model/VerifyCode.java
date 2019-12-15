package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/15 0015 20:06
 */
public class VerifyCode implements Serializable {
    private String verifyCode;

    public VerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
