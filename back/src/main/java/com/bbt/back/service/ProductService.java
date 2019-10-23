package com.bbt.back.service;

import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.model.LikeObject;
import com.bbt.back.model.ProductObject;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    int deleteProduct(LikeObject likeObject);

    PageInfo<Object> selectByTypeAndLibrary(int type,int library,Integer pageNum, Integer pageSize);

    int changeState(ProductObject productObject);

    List<Object> getHotProduct();

    PageInfo<Object> selectByToken(int type, String searchToken, Integer pageNum, Integer pageSize);

    void likeProduct(LikeObject likeObject);

    Object selectByProductId(LikeObject likeObject);
}
