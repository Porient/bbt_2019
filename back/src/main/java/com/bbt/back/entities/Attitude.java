package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/22 0022 16:26
 */
public class Attitude implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer attitude_id;
    private Integer userId;
    private Integer productId;
    private Integer productType;
    private Integer attitude;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAttitude_id() {
        return attitude_id;
    }

    public void setAttitude_id(Integer attitude_id) {
        this.attitude_id = attitude_id;
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

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    @Override
    public String toString() {
        return "Attitude{" +
                "attitude_id=" + attitude_id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productType=" + productType +
                ", attitude=" + attitude +
                '}';
    }
}
