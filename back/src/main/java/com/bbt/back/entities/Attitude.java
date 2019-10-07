package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 用户对产品态度实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:08
 */
public class Attitude implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer attitudeId;
    private Integer userId;
    private Integer productId;
    private String attitude;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAttitudeId() {
        return attitudeId;
    }

    public void setAttitudeId(Integer attitudeId) {
        this.attitudeId = attitudeId;
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

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    @Override
    public String toString() {
        return "Attitude{" +
                "attitudeId=" + attitudeId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", attitude='" + attitude + '\'' +
                '}';
    }
}
