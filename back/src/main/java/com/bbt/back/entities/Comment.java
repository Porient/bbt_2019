package com.bbt.back.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 评论实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:09
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer commentId;
    private Integer userId;
    private Integer productId;
    private Integer productType;
    private String productName;
    private String content;
    private Integer likeNum;
    private Date date;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productType=" + productType +
                ", productName='" + productName + '\'' +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", date=" + date +
                '}';
    }
}
