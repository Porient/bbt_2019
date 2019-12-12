package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 20:37
 */
public class SearchObject implements Serializable {
    private int type;
    private String searchStr;

    public SearchObject(){

    }

    public SearchObject(int type, String searchStr) {
        this.type = type;
        this.searchStr = searchStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
}
