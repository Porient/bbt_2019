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
import com.bbt.back.model.SearchProDto;
import com.bbt.back.service.ProductService;
import com.bbt.back.utils.DeduplicationUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
        if (likeObject.getType() == 0) {
            return phoneDao.deletePhone(likeObject.getProductId());
        } else {
            return computerDao.deleteComputer(likeObject.getProductId());
        }
    }

    @Override
    public PageInfo<Object> selectByTypeAndLibrary(int type, int library, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == -1 ? 1 : pageNum;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Object> list;
        if (type == 0) {
            list = phoneDao.selectByLibrary(library);
        } else {
            list = computerDao.selectByLibrary(library);
        }
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Object> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int changeState(ProductObject productObject) {
        if (productObject.getProductType() == 0) {
            return phoneDao.changeState(productObject.getProductId(), 1 - productObject.getProductState());
        } else {
            return computerDao.changeState(productObject.getProductId(), 1 - productObject.getProductState());
        }
    }

    @Override
    public List<Object> getHotProduct() {
        List<Object> products = new ArrayList<>();
        List<ProductLike> productLikes = productLikeDao.getHotProduct();
        for (ProductLike productLike : productLikes) {
            if (productLike.getProductType() == 0) {
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
        List<Object> list = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        String[] tokens = searchToken.split("%");
        Double size=Double.valueOf(tokens.length);
        if (type == 0) {
            for (int i=0;i<tokens.length;i++){
                list.addAll(phoneDao.selectByToken(searchToken));
            }
        } else {
            for (int i=0;i<tokens.length;i++) {
                list.addAll(computerDao.selectByToken(searchToken));
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        list= DeduplicationUtil.deduplication(list);
        if (type == 0) {
            for (int j=0;j<list.size();j++){
                Phone phone= (Phone) list.get(j);
                String phoneStr=phone.toString();
                Double num=0.0;
                for (int i=0;i<tokens.length;i++){
                    if (phoneStr.contains(tokens[i])){
                        num++;
                    }
                }
                Double suitability=num/size;
                SearchProDto searchProDto=new SearchProDto(phone,suitability);
                result.add(searchProDto);
            }
        } else {
            for (int j=0;j<list.size();j++){
                Computer computer= (Computer) list.get(j);
                String phoneStr=computer.toString();
                Double num=0.0;
                for (int i=0;i<tokens.length;i++){
                    if (phoneStr.contains(tokens[i])){
                        num++;
                    }
                }
                Double suitability=num/size;
                SearchProDto searchProDto=new SearchProDto(computer,suitability);
                result.add(searchProDto);
            }
        }

        PageInfo<Object> pageInfo = new PageInfo<>(result);
        return pageInfo;
    }

    @Override
    public int likeProduct(ProductLikeObject likeObject, Integer userId) {
        UserLike userLike = userLikeDao.findByProductIdAndType(userId, likeObject.getProductId(), likeObject.getType());
        ProductLike productLike = productLikeDao.findByProductIdAndType(likeObject.getProductId(), likeObject.getType());
        int oldNum = productLike.getLikeNum();
        //首先判断用户是否点赞
        if (userLike == null) {
            userLike = new UserLike(userId, likeObject.getProductId(), likeObject.getType(), new Date());
            userLikeDao.insertUserLike(userLike);
        } else {
            userLikeDao.deleteUserLike(userId, likeObject.getProductId(), likeObject.getType());
            productLike.setLikeNum(oldNum - 1);
            productLikeDao.updateProductLike(productLike);
            return -1;
        }
        if (productLike == null) {
            productLike = new ProductLike(likeObject.getProductId(), likeObject.getType(), 1);
            productLikeDao.insertProductLike(productLike);
        } else {
            productLike.setLikeNum(oldNum + 1);
            productLikeDao.updateProductLike(productLike);
        }
        return 1;
    }

    @Override
    public Object selectByProductId(ProductLikeObject likeObject) {
        if (likeObject.getType() == 0) {
            return phoneDao.selectByProductId(likeObject.getProductId());
        } else {
            return computerDao.selectByProductId(likeObject.getProductId());
        }
    }

    @Override
    public ProductResult findByProductIdAndType(ProductLikeObject likeObject) {
        ProductResult productResult = new ProductResult();
        if (likeObject.getType() == 0) {
            Phone phone = phoneDao.findPhoneById(likeObject.getProductId());
            productResult.setProductName(phone.getProductName());
            productResult.setProductPicture(phone.getAppearance1());
        } else {
            Computer computer = computerDao.findComputerById(likeObject.getProductId());
            productResult.setProductName(computer.getBrand());
            productResult.setProductPicture(computer.getAppearance1());
        }
        return productResult;
    }

    @Override
    public String getRecommendProduct(Integer userId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\recommend\\recommend_top_n.py "+userId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getBasicInfo(int productId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\data_process\\getBasicInfo.py "+productId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getStatisticInfo(int productId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\data_process\\getStatisticInfo.py "+productId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getCompareInfo(int productId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\data_process\\getCompareInfo.py "+productId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getCommentInfo(int productId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\data_process\\getCommentInfo.py "+productId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getMiningInfo(int productId) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\main\\python\\data_process\\getMiningInfo.py "+productId);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            System.out.println(result);
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getProductName(Integer productId, Integer productType) {
        String productName="";
        if(productType==0){
            Phone phone=phoneDao.findPhoneById(productId);
            productName=phone.getProductName();
        }else {
            Computer computer=computerDao.findComputerById(productId);
            productName=computer.getBrand();
        }
        return productName;
    }
}
