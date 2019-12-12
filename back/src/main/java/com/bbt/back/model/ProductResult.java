package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/26 0026 9:44
 */
public class ProductResult implements Serializable {
    private String productName;
    private String productPicture;

    public ProductResult(String productName, String productPicture) {
        this.productName = productName;
        this.productPicture = productPicture;
    }

    public ProductResult() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
}
