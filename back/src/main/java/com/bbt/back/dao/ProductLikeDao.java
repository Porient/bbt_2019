package com.bbt.back.dao;

import com.bbt.back.entities.ProductLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductLikeDao {
    List<ProductLike> getHotProduct();

    int insertProductLike(ProductLike productLike);

    ProductLike findByProductIdAndType(@Param("productId")int productId, @Param("productType")int productType);

    int updateProductLike(ProductLike productLike);
}
