package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 21:07
 */
public class ProductLikeObject implements Serializable {
    private int type;
    private int productId;

    public ProductLikeObject(int type, int productId) {
        this.type = type;
        this.productId = productId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
