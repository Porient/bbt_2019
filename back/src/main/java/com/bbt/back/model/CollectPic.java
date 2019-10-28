package com.bbt.back.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/28 0028 16:26
 */
public class CollectPic implements Serializable {
    Integer collectNum;
    Integer rank;
    HashMap<String,Integer> map;

    public CollectPic() {
    }

    public CollectPic(Integer collectNum, Integer rank, HashMap<String, Integer> map) {
        this.collectNum = collectNum;
        this.rank = rank;
        this.map = map;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
