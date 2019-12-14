package com.bbt.back.service;

import com.bbt.back.model.ProductLikeObject;
import com.bbt.back.model.ProductObject;
import com.bbt.back.model.ProductResult;
import com.github.pagehelper.PageInfo;
import org.python.core.PyObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    int deleteProduct(ProductLikeObject likeObject);

    PageInfo<Object> selectByTypeAndLibrary(int type,int library,Integer pageNum, Integer pageSize);

    int changeState(ProductObject productObject);

    List<Object> getHotProduct();

    PageInfo<Object> selectByToken(int type, String searchToken, Integer pageNum, Integer pageSize);

    int likeProduct(ProductLikeObject likeObject,Integer userId);

    Object selectByProductId(ProductLikeObject likeObject);

    ProductResult findByProductIdAndType(ProductLikeObject likeObject);

    String getRecommendProduct(Integer userId);

    PyObject getBasicInfo(int productId);

    PyObject getStatisticInfo(int productId);

    PyObject getCompareInfo(int productId);

    PyObject getCommentInfo(int productId);

    String getMiningInfo(int productId);
}
