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
    private String productName;
    private String productPicture;
    private Date browseTime;
    private Integer browseNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    @Override
    public String toString() {
        return "Record{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPicture='" + productPicture + '\'' +
                ", browseTime=" + browseTime +
                ", browseNum=" + browseNum +
                '}';
    }
}
