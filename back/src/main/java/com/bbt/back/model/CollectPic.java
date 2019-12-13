package com.bbt.back.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/28 0028 16:26
 */
public class CollectPic implements Serializable {
    Long collectNum;
    Integer rank;
    HashMap<String,Integer> brandMap;
    HashMap<String,Integer> tagMap;

    public CollectPic() {
    }

    public CollectPic(Long collectNum, Integer rank, HashMap<String, Integer> brandMap, HashMap<String, Integer> tagMap) {
        this.collectNum = collectNum;
        this.rank = rank;
        this.brandMap = brandMap;
        this.tagMap = tagMap;
    }

    public HashMap<String, Integer> getBrandMap() {
        return brandMap;
    }

    public void setBrandMap(HashMap<String, Integer> brandMap) {
        this.brandMap = brandMap;
    }

    public HashMap<String, Integer> getTagMap() {
        return tagMap;
    }

    public void setTagMap(HashMap<String, Integer> tagMap) {
        this.tagMap = tagMap;
    }

    public Long getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Long collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
