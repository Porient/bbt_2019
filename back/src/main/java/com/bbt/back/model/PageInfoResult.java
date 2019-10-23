package com.bbt.back.model;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/23 0023 15:57
 */
public class PageInfoResult {
    private Object object;
    private Long total;
    private Integer pageNum;
    private Integer pageSize;

    public PageInfoResult(){

    }

    public PageInfoResult(Object object, Long total, Integer pageNum, Integer pageSize) {
        this.object = object;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
