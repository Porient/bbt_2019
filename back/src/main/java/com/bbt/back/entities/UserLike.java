package com.bbt.back.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/22 0022 15:31
 */
public class UserLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int userLikeId;
    private int userId;
    private int productId;
    private int productType;
    private Date likeTime;

    public UserLike(){

    }

    public UserLike(int userId, int productId, int productType, Date likeTime) {
        this.userId = userId;
        this.productId = productId;
        this.productType = productType;
        this.likeTime = likeTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserLikeId() {
        return userLikeId;
    }

    public void setUserLikeId(int userLikeId) {
        this.userLikeId = userLikeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "UserLike{" +
                "userLikeId=" + userLikeId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productType=" + productType +
                ", likeTime=" + likeTime +
                '}';
    }
}
