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
    private String computerName;
    private String vendor;
    private Integer afterSalesAbility;
    private Integer premiumAbility;
    private String CPU;
    private String GPU;
    private Integer runningMemory;
    private Integer storageMemoryType;
    private Integer storageMemorySize;
    private Integer bodyWeight;
    private Integer lifeTime;
    private Integer keyBoardType;
    private Integer unlockMode;
    private String camera;
    private String soundSystem;
    private String heatDissipation;
    private String appearance1;
    private String appearance2;
    private String appearance3;
    private String screen;
    private Integer numOfInterfaces;
    private String productCategory;
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

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getAfterSalesAbility() {
        return afterSalesAbility;
    }

    public void setAfterSalesAbility(Integer afterSalesAbility) {
        this.afterSalesAbility = afterSalesAbility;
    }

    public Integer getPremiumAbility() {
        return premiumAbility;
    }

    public void setPremiumAbility(Integer premiumAbility) {
        this.premiumAbility = premiumAbility;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public Integer getRunningMemory() {
        return runningMemory;
    }

    public void setRunningMemory(Integer runningMemory) {
        this.runningMemory = runningMemory;
    }

    public Integer getStorageMemoryType() {
        return storageMemoryType;
    }

    public void setStorageMemoryType(Integer storageMemoryType) {
        this.storageMemoryType = storageMemoryType;
    }

    public Integer getStorageMemorySize() {
        return storageMemorySize;
    }

    public void setStorageMemorySize(Integer storageMemorySize) {
        this.storageMemorySize = storageMemorySize;
    }

    public Integer getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Integer bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Integer getKeyBoardType() {
        return keyBoardType;
    }

    public void setKeyBoardType(Integer keyBoardType) {
        this.keyBoardType = keyBoardType;
    }

    public Integer getUnlockMode() {
        return unlockMode;
    }

    public void setUnlockMode(Integer unlockMode) {
        this.unlockMode = unlockMode;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(String soundSystem) {
        this.soundSystem = soundSystem;
    }

    public String getHeatDissipation() {
        return heatDissipation;
    }

    public void setHeatDissipation(String heatDissipation) {
        this.heatDissipation = heatDissipation;
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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Integer getNumOfInterfaces() {
        return numOfInterfaces;
    }

    public void setNumOfInterfaces(Integer numOfInterfaces) {
        this.numOfInterfaces = numOfInterfaces;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
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
                ", computerName='" + computerName + '\'' +
                ", vendor='" + vendor + '\'' +
                ", afterSalesAbility=" + afterSalesAbility +
                ", premiumAbility=" + premiumAbility +
                ", CPU='" + CPU + '\'' +
                ", GPU='" + GPU + '\'' +
                ", runningMemory=" + runningMemory +
                ", storageMemoryType=" + storageMemoryType +
                ", storageMemorySize=" + storageMemorySize +
                ", bodyWeight=" + bodyWeight +
                ", lifeTime=" + lifeTime +
                ", keyBoardType=" + keyBoardType +
                ", unlockMode=" + unlockMode +
                ", camera='" + camera + '\'' +
                ", soundSystem='" + soundSystem + '\'' +
                ", heatDissipation='" + heatDissipation + '\'' +
                ", appearance1='" + appearance1 + '\'' +
                ", appearance2='" + appearance2 + '\'' +
                ", appearance3='" + appearance3 + '\'' +
                ", screen='" + screen + '\'' +
                ", numOfInterfaces=" + numOfInterfaces +
                ", productCategory='" + productCategory + '\'' +
                ", library=" + library +
                ", productAnalysis='" + productAnalysis + '\'' +
                '}';
    }
}
