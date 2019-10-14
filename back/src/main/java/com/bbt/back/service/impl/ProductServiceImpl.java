package com.bbt.back.service.impl;

import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.dao.ProductLikeDao;
import com.bbt.back.entities.Comment;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.ProductLike;
import com.bbt.back.model.ProductList;
import com.bbt.back.model.ProductObject;
import com.bbt.back.service.ProductService;
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
    public int deleteProduct(int productId) {
        if (phoneDao.selectByProductId(productId) == 0 || computerDao.selectByProductId(productId) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public ProductList selectAllPre() {
        List<Phone> phones = phoneDao.selectAllPre();
        List<Computer> computers = computerDao.selectAllPre();
        return new ProductList(phones,computers);
    }

    @Override
    public ProductList selectAllFormal() {
        List<Phone> phones = phoneDao.selectAllFormal();
        List<Computer> computers = computerDao.selectAllFormal();
        return new ProductList(phones,computers);
    }

    @Override
    public ProductList selectByType(int type) {
        List<Phone> phones = phoneDao.selectByType(type);
        List<Computer> computers = computerDao.selectByType(type);
        return new ProductList(phones,computers);
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
    public List<Object> selectByToken(int type, String searchToken) {
        if (type == 0){
            return phoneDao.selectByToken(searchToken);
        } else {
            return computerDao.selectByToken(searchToken);
        }
    }
}
