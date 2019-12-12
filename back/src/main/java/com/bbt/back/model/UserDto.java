package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/12 0012 10:42
 */
public class UserDto implements Serializable {
    private Integer userId;

    public UserDto(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
