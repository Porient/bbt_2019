package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 18:47
 */
public class ProductLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productLikeId;
    private int productId;
    private int productType;
    private int likeNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ProductLike() {
    }


    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public ProductLike(int productId, int productType, int likeNum) {
        this.productId = productId;
        this.productType = productType;
        this.likeNum = likeNum;
    }

    public ProductLike(int productLikeId, int productId, int productType, int likeNum) {
        this.productLikeId = productLikeId;
        this.productId = productId;
        this.productType = productType;
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "ProductLike{" +
                "productLikeId=" + productLikeId +
                ", productId=" + productId +
                ", productType=" + productType +
                ", likeNum=" + likeNum +
                '}';
    }

    public int getProductLikeId() {
        return productLikeId;
    }

    public void setProductLikeId(int productLikeId) {
        this.productLikeId = productLikeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeCount) {
        this.likeNum = likeNum;
    }

}
