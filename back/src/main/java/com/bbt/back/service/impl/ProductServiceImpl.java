package com.bbt.back.service.impl;

import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.dao.ProductLikeDao;
import com.bbt.back.dao.UserLikeDao;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.ProductLike;
import com.bbt.back.entities.UserLike;
import com.bbt.back.model.ProductLikeObject;
import com.bbt.back.model.ProductObject;
import com.bbt.back.model.ProductResult;
import com.bbt.back.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 19:35
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private PhoneDao phoneDao;
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private ProductLikeDao productLikeDao;
    @Autowired
    private UserLikeDao userLikeDao;

    @Override
    public int deleteProduct(ProductLikeObject likeObject) {
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
    public int likeProduct(ProductLikeObject likeObject,Integer userId) {
        UserLike userLike=userLikeDao.findByProductIdAndType(userId,likeObject.getProductId(),likeObject.getType());
        ProductLike productLike=productLikeDao.findByProductIdAndType(likeObject.getProductId(),likeObject.getType());
        int oldNum=productLike.getLikeNum();
        //首先判断用户是否点赞
        if (userLike==null){
            userLike=new UserLike(userId,likeObject.getProductId(),likeObject.getType(),new Date());
            userLikeDao.insertUserLike(userLike);
        }else {
            userLikeDao.deleteUserLike(userId,likeObject.getProductId(),likeObject.getType());
            productLike.setLikeNum(oldNum-1);
            productLikeDao.updateProductLike(productLike);
            return -1;
        }
        if (productLike==null){
            productLike=new ProductLike(likeObject.getProductId(),likeObject.getType(),1);
            productLikeDao.insertProductLike(productLike);
        }else {
            productLike.setLikeNum(oldNum+1);
            productLikeDao.updateProductLike(productLike);
        }
        return 1;
    }

    @Override
    public Object selectByProductId(ProductLikeObject likeObject) {
        if (likeObject.getType() == 0){
            return phoneDao.selectByProductId(likeObject.getProductId());
        } else {
            return computerDao.selectByProductId(likeObject.getProductId());
        }
    }

    @Override
    public ProductResult findByProductIdAndType(ProductLikeObject likeObject) {
        ProductResult productResult =new ProductResult();
        if (likeObject.getType()==0){
            Phone phone=phoneDao.findPhoneById(likeObject.getProductId());
            productResult.setProductName(phone.getProductName());
            productResult.setProductPicture(phone.getAppearance1());
        }else {
            Computer computer = computerDao.findComputerById(likeObject.getProductId());
            productResult.setProductName(computer.getBrand());
            productResult.setProductPicture(computer.getAppearance1());
        }
        return productResult;
    }

    @Override
    public String getRecommendProduct(Integer userId) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(System.getProperty("user.dir")+"/src/main/python/recommend/recommend_top_n.py ");
        PyFunction func = (PyFunction) interpreter.get("recommendBoth",
                PyFunction.class);

        PyObject pyobj = func.__call__(new PyInteger(userId));
        return pyobj.toString();
    }

    @Override
    public int genPhoneReport(int productId) {
        PythonInterpreter interpreter = new PythonInterpreter();
        String productAnalysis;
        Phone phone=phoneDao.findPhoneById(productId);
        interpreter.execfile(System.getProperty("user.dir")+"/src/main/python/data_process/data_mining.py ");
        //basicInfo
        PyFunction func = (PyFunction) interpreter.get("getBasicInfo",
                PyFunction.class);
        PyObject basicInfoObj = func.__call__(new PyInteger(productId));
        String basicInfo=basicInfoObj.toString();
        //statisticInfo
        func=(PyFunction) interpreter.get("getStatisticInfo",
                PyFunction.class);
        PyObject statisticInfoObj = func.__call__(new PyInteger(productId));
        String statisticInfo=statisticInfoObj.toString();
        //compareInfo
        func=(PyFunction) interpreter.get("getCompareInfo",
                PyFunction.class);
        PyObject compareInfoObj = func.__call__(new PyInteger(productId));
        String compareInfo=compareInfoObj.toString();
        //commentInfo
        func=(PyFunction) interpreter.get("getCommentInfo",
                PyFunction.class);
        PyObject commentInfoObj = func.__call__(new PyInteger(productId));
        String commentInfo=commentInfoObj.toString();
        //miningInfo
        func=(PyFunction) interpreter.get("getMiningInfo",
                PyFunction.class);
        PyObject miningInfoObj = func.__call__(new PyInteger(productId));
        String miningInfo=miningInfoObj.toString();
        //合成分析
        productAnalysis="basicInfo: "+basicInfo+" statisticInfo: "+statisticInfo+ " compareInfo: "+ compareInfo;
        phone.setProductAnalysis(productAnalysis);
        return phoneDao.updatePhone(phone);
    }
}
