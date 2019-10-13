package com.bbt.back.model;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 20:26
 */
public class ProductObject {
    private int productType;
    private int productState;
    private int productIId;

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

    public int getProductIId() {
        return productIId;
    }

    public void setProductIId(int productIId) {
        this.productIId = productIId;
    }
}
