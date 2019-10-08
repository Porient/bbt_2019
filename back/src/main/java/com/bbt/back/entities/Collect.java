package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 用户收藏实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 14:56
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer collectId;
    private Integer userId;
    private Integer productId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
