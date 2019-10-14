package com.bbt.back.dao;

import com.bbt.back.entities.ProductLike;

import java.util.List;

//新增
public interface ProductLikeDao {
    List<ProductLike> getHotProduct();
}
