package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:23
 */
public class Computer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer productId;
    private String brand;
    private String conPerformance;
    private String gamePerformance;
    private String interfaces;
    private String storage;
    private String sound;
    private String camera;
    private String specification;
    private String peripheral;
    private String internet;
    private String screen;
    private String endurance;
    private String other;
    private Double price;
    private String url;
    private String appearance1;
    private String appearance2;
    private String appearance3;
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

    public String getConPerformance() {
        return conPerformance;
    }

    public void setConPerformance(String conPerformance) {
        this.conPerformance = conPerformance;
    }

    public String getGamePerformance() {
        return gamePerformance;
    }

    public void setGamePerformance(String gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    public String getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(String peripheral) {
        this.peripheral = peripheral;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getEndurance() {
        return endurance;
    }

    public void setEndurance(String endurance) {
        this.endurance = endurance;
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

    @Override
    public String toString() {
        return "Computer{" +
                "productId=" + productId +
                ", brand='" + brand + '\'' +
                ", conPerformance='" + conPerformance + '\'' +
                ", gamePerformance='" + gamePerformance + '\'' +
                ", interfaces='" + interfaces + '\'' +
                ", storage='" + storage + '\'' +
                ", sound='" + sound + '\'' +
                ", camera='" + camera + '\'' +
                ", specification='" + specification + '\'' +
                ", peripheral='" + peripheral + '\'' +
                ", internet='" + internet + '\'' +
                ", screen='" + screen + '\'' +
                ", endurance='" + endurance + '\'' +
                ", other='" + other + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", appearance1='" + appearance1 + '\'' +
                ", appearance2='" + appearance2 + '\'' +
                ", appearance3='" + appearance3 + '\'' +
                ", productType=" + productType +
                ", library=" + library +
                ", productAnalysis='" + productAnalysis + '\'' +
                '}';
    }
}
