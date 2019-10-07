package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 爬虫参数实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:12
 */
public class Crawler implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer parameterId;
    private String crawlAddress;
    private Integer timeInterval;
    private String scriptName;
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getCrawlAddress() {
        return crawlAddress;
    }

    public void setCrawlAddress(String crawlAddress) {
        this.crawlAddress = crawlAddress;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Crawler{" +
                "parameterId=" + parameterId +
                ", crawlAddress='" + crawlAddress + '\'' +
                ", timeInterval=" + timeInterval +
                ", scriptName='" + scriptName + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
