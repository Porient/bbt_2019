package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 20:26
 */
public class ProductObject implements Serializable {
    private int productType;
    private int productState;
    private int productId;

    public ProductObject(){
        
    }

    public ProductObject(int productType, int productState, int productId) {
        this.productType = productType;
        this.productState = productState;
        this.productId = productId;
    }

    public int getProductType() {
        return productType;
    }

    public int getProductState() {
        return productState;
    }

    public void setProductState(int productState) {
        this.productState = productState;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductObject{" +
                "productType=" + productType +
                ", productState=" + productState +
                ", productId=" + productId +
                '}';
    }
}
