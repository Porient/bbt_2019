package com.bbt.back.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:19
 */
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer recordId;
    private Integer userId;
    private Integer productId;
    private Integer productType;
    private String productName;
    private String productPicture;
    private Date browseTime;

    public Record(){

    }

    public Record(Integer userId, Integer productId, Integer productType, String productName, String productPicture, Date browseTime) {
        this.userId = userId;
        this.productId = productId;
        this.productType = productType;
        this.productName = productName;
        this.productPicture = productPicture;
        this.browseTime = browseTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productType=" + productType +
                ", productName='" + productName + '\'' +
                ", productPicture='" + productPicture + '\'' +
                ", browseTime=" + browseTime +
                '}';
    }
}
