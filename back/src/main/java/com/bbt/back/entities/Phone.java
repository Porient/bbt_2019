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
    private String phoneName;
    private String vendor;
    private Integer afterSalesAbility;
    private Integer premiumAbility;
    private String frontCamera;
    private String rearCamera;
    private Integer cameraCapability;
    private String screenSurface;
    private String fingerprintModule;
    private String bodyColor;
    private String picture1;
    private String picture2;
    private String picture3;
    private Integer bodyWeight;
    private Integer bodyMaterial;
    private Integer resolution;
    private String screenManufacturer;
    private Integer batteryCapacity;
    private String chargingSpeed;
    private String actualPerformance;
    private String headphonePlug;
    private String waterproofLevel;
    private Integer salesVolume;
    private String chip;
    private String speaker;
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

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
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

    public Integer getCameraCapability() {
        return cameraCapability;
    }

    public void setCameraCapability(Integer cameraCapability) {
        this.cameraCapability = cameraCapability;
    }

    public String getScreenSurface() {
        return screenSurface;
    }

    public void setScreenSurface(String screenSurface) {
        this.screenSurface = screenSurface;
    }

    public String getFingerprintModule() {
        return fingerprintModule;
    }

    public void setFingerprintModule(String fingerprintModule) {
        this.fingerprintModule = fingerprintModule;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public Integer getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Integer bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Integer getBodyMaterial() {
        return bodyMaterial;
    }

    public void setBodyMaterial(Integer bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public String getScreenManufacturer() {
        return screenManufacturer;
    }

    public void setScreenManufacturer(String screenManufacturer) {
        this.screenManufacturer = screenManufacturer;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getChargingSpeed() {
        return chargingSpeed;
    }

    public void setChargingSpeed(String chargingSpeed) {
        this.chargingSpeed = chargingSpeed;
    }

    public String getActualPerformance() {
        return actualPerformance;
    }

    public void setActualPerformance(String actualPerformance) {
        this.actualPerformance = actualPerformance;
    }

    public String getHeadphonePlug() {
        return headphonePlug;
    }

    public void setHeadphonePlug(String headphonePlug) {
        this.headphonePlug = headphonePlug;
    }

    public String getWaterproofLevel() {
        return waterproofLevel;
    }

    public void setWaterproofLevel(String waterproofLevel) {
        this.waterproofLevel = waterproofLevel;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
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
        return "Phone{" +
                "productId=" + productId +
                ", phoneName='" + phoneName + '\'' +
                ", vendor='" + vendor + '\'' +
                ", afterSalesAbility=" + afterSalesAbility +
                ", premiumAbility=" + premiumAbility +
                ", frontCamera='" + frontCamera + '\'' +
                ", rearCamera='" + rearCamera + '\'' +
                ", cameraCapability=" + cameraCapability +
                ", screenSurface='" + screenSurface + '\'' +
                ", fingerprintModule='" + fingerprintModule + '\'' +
                ", bodyColor='" + bodyColor + '\'' +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", picture3='" + picture3 + '\'' +
                ", bodyWeight=" + bodyWeight +
                ", bodyMaterial=" + bodyMaterial +
                ", resolution=" + resolution +
                ", screenManufacturer='" + screenManufacturer + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", chargingSpeed='" + chargingSpeed + '\'' +
                ", actualPerformance='" + actualPerformance + '\'' +
                ", headphonePlug='" + headphonePlug + '\'' +
                ", waterproofLevel='" + waterproofLevel + '\'' +
                ", salesVolume=" + salesVolume +
                ", chip='" + chip + '\'' +
                ", speaker='" + speaker + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", library=" + library +
                ", productAnalysis='" + productAnalysis + '\'' +
                '}';
    }
}
