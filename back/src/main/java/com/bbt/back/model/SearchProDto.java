package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/14 0014 11:49
 */
public class SearchProDto implements Serializable {
    private Object product;
    private Double suitability;

    public SearchProDto() {
    }

    public SearchProDto(Object product, Double suitability) {
        this.product = product;
        this.suitability = suitability;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    public Double getSuitability() {
        return suitability;
    }

    public void setSuitability(Double suitability) {
        this.suitability = suitability;
    }

    @Override
    public String toString() {
        return "SearchProDto{" +
                "product=" + product +
                ", suitability=" + suitability +
                '}';
    }
}
