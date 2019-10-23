package com.bbt.back.service.impl;

import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.dao.ProductLikeDao;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.ProductLike;
import com.bbt.back.model.LikeObject;
import com.bbt.back.model.ProductObject;
import com.bbt.back.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 19:35
 */
public class ProductServiceImpl implements ProductService {
    @Autowired
    private PhoneDao phoneDao;
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private ProductLikeDao productLikeDao;
    @Override
    public int deleteProduct(LikeObject likeObject) {
        if (likeObject.getType() == 0){
            phoneDao.deletePhone(likeObject.getProductId());
        } else {
            computerDao.deleteComputer(likeObject.getProductId());
        }
        return 0;
    }

    @Override
    public PageInfo<Object> selectByTypeAndLibrary(int type, int library, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == -1 ? 1 : pageNum;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Object> list;
        if (type == 0){
            list=phoneDao.selectByLibrary(library);
        } else {
            list=computerDao.selectByLibrary(library);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Object> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int changeState(ProductObject productObject) {
        if (productObject.getProductType() == 0){
            return phoneDao.changeState(productObject.getProductIId(),1-productObject.getProductState());
        } else {
            return computerDao.changeState(productObject.getProductIId(),1-productObject.getProductState());
        }
    }

    @Override
    public List<Object> getHotProduct() {
        List<Object> products = new ArrayList<>();
        List<ProductLike> productLikes = productLikeDao.getHotProduct();
        for (ProductLike productLike : productLikes){
            if (productLike.getProductType() == 0){
                products.add(phoneDao.findPhoneById(productLike.getProductId()));
            } else {
                products.add(computerDao.findComputerById(productLike.getProductId()));
            }
        }
        return products;
    }

    @Override
    public PageInfo<Object> selectByToken(int type, String searchToken, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == -1 ? 1 : pageNum;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Object> list;
        if (type == 0){
            list=phoneDao.selectByToken(searchToken);
        } else {
            list=computerDao.selectByToken(searchToken);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Object> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void likeProduct(LikeObject likeObject) {
        if (likeObject.getType() == 0){
            phoneDao.likePhone(likeObject.getProductId());
        } else {
            computerDao.likeComputer(likeObject.getProductId());
        }
    }

    @Override
    public Object selectByProductId(LikeObject likeObject) {
        if (likeObject.getType() == 0){
            return phoneDao.selectByProductId(likeObject.getProductId());
        } else {
            return computerDao.selectByProductId(likeObject.getProductId());
        }
    }
}
