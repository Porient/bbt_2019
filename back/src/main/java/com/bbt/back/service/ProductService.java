package com.bbt.back.service;

import com.bbt.back.model.ProductList;
import com.bbt.back.model.ProductObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    int deleteProduct(int productId);

    ProductList selectAllPre();

    ProductList selectAllFormal();

    ProductList selectByType(int type);

    int changeState(ProductObject productObject);

    List<Object> getHotProduct();

    List<Object> selectByToken(int type, String searchToken);
}
