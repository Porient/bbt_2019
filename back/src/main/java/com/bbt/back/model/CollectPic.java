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
    HashMap<String,Long> map;

    public CollectPic() {
    }

    public CollectPic(Long collectNum, Integer rank, HashMap<String, Long> map) {
        this.collectNum = collectNum;
        this.rank = rank;
        this.map = map;
    }

    public HashMap<String, Long> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Long> map) {
        this.map = map;
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
