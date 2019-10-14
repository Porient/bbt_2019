package com.bbt.back.model;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 20:37
 */
public class SearchObject {
    private int type;
    private String searchStr;

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
