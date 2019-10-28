package com.bbt.back.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/28 0028 15:44
 */
public class RecordPic implements Serializable {
    Integer recordNum;
    Integer computerNum;
    Integer phoneNum;
    HashMap<String,Integer> rocordMap;
    HashMap<String,Integer> timeMap;

    public RecordPic(){

    }

    public RecordPic(Integer recordNum, Integer computerNum, Integer phoneNum, HashMap<String, Integer> rocordMap, HashMap<String, Integer> timeMap) {
        this.recordNum = recordNum;
        this.computerNum = computerNum;
        this.phoneNum = phoneNum;
        this.rocordMap = rocordMap;
        this.timeMap = timeMap;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public Integer getComputerNum() {
        return computerNum;
    }

    public void setComputerNum(Integer computerNum) {
        this.computerNum = computerNum;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public HashMap<String, Integer> getRocordMap() {
        return rocordMap;
    }

    public void setRocordMap(HashMap<String, Integer> rocordMap) {
        this.rocordMap = rocordMap;
    }

    public HashMap<String, Integer> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(HashMap<String, Integer> timeMap) {
        this.timeMap = timeMap;
    }
}
