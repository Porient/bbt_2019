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
    private int likeCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ProductLike() {
    }

    public ProductLike(int productLikeId, int productId, int likeCount) {
        this.productLikeId = productLikeId;
        this.productId = productId;
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "ProductLike{" +
                "productLikeId=" + productLikeId +
                ", productId=" + productId +
                ", likeCount=" + likeCount +
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
