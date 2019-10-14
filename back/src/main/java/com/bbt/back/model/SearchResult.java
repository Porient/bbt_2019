package com.bbt.back.model;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 20:08
 */
public class SearchResult {
    private List<String> words;
    private List<Object> products;

    public SearchResult() {
    }

    public SearchResult(List<String> words, List<Object> products) {
        this.words = words;
        this.products = products;
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
}
