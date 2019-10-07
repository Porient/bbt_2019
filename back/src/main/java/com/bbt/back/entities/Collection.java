package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 用户收藏实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 14:56
 */
public class Collection implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer collectionId;
    private Integer userId;
    private Integer productId;

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
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
        return "Collection{" +
                "collectionId=" + collectionId +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
