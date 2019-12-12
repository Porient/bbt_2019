package com.bbt.back.model;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 20:08
 */
public class SearchResult implements Serializable {
    private List<String> words;
    private List<Object> products;
    private Long total;
    private Integer pageNum;
    private Integer pageSize;

    public SearchResult() {
    }

    public SearchResult(List<String> words, List<Object> products, Long total, Integer pageNum, Integer pageSize) {
        this.words = words;
        this.products = products;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<Object> getProducts() {
        return products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
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

