package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:23
 */
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer productId;
    private String brand;
    private String productName;
    private String performance;
    private String interfaces;
    private String frontCamera;
    private String rearCamera;
    private String photoFeatures;
    private String body;
    private String communication;
    private String endurance;
    private String screen;
    private String other;
    private Double price;
    private String url;
    private String appearance1;
    private String appearance2;
    private String appearance3;
    private String tag1;
    private String tag2;
    private String tag3;
    private int productType;
    private Integer library;
    private String productAnalysis;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public void setRearCamera(String rearCamera) {
        this.rearCamera = rearCamera;
    }

    public String getPhotoFeatures() {
        return photoFeatures;
    }

    public void setPhotoFeatures(String photoFeatures) {
        this.photoFeatures = photoFeatures;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getEndurance() {
        return endurance;
    }

    public void setEndurance(String endurance) {
        this.endurance = endurance;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppearance1() {
        return appearance1;
    }

    public void setAppearance1(String appearance1) {
        this.appearance1 = appearance1;
    }

    public String getAppearance2() {
        return appearance2;
    }

    public void setAppearance2(String appearance2) {
        this.appearance2 = appearance2;
    }

    public String getAppearance3() {
        return appearance3;
    }

    public void setAppearance3(String appearance3) {
        this.appearance3 = appearance3;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Integer getLibrary() {
        return library;
    }

    public void setLibrary(Integer library) {
        this.library = library;
    }

    public String getProductAnalysis() {
        return productAnalysis;
    }

    public void setProductAnalysis(String productAnalysis) {
        this.productAnalysis = productAnalysis;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "productId=" + productId +
                ", brand='" + brand + '\'' +
                ", productName='" + productName + '\'' +
                ", performance='" + performance + '\'' +
                ", interfaces='" + interfaces + '\'' +
                ", frontCamera='" + frontCamera + '\'' +
                ", rearCamera='" + rearCamera + '\'' +
                ", photoFeatures='" + photoFeatures + '\'' +
                ", body='" + body + '\'' +
                ", communication='" + communication + '\'' +
                ", endurance='" + endurance + '\'' +
                ", screen='" + screen + '\'' +
                ", other='" + other + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", appearance1='" + appearance1 + '\'' +
                ", appearance2='" + appearance2 + '\'' +
                ", appearance3='" + appearance3 + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", productType=" + productType +
                ", library=" + library +
                ", productAnalysis='" + productAnalysis + '\'' +
                '}';
    }
}
