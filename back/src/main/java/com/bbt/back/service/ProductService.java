package com.bbt.back.service;

import com.bbt.back.model.LikeObject;
import com.bbt.back.model.ProductList;
import com.bbt.back.model.ProductObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    int deleteProduct(LikeObject likeObject);

    ProductList selectAllPre();

    ProductList selectAllFormal();

    ProductList selectByType(int type);

    int changeState(ProductObject productObject);

    List<Object> getHotProduct();

    List<Object> selectByToken(int type, String searchToken);

    void likeProduct(LikeObject likeObject);

    Object selectByProductId(LikeObject likeObject);
}
