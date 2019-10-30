package com.bbt.back.web;

import com.bbt.back.entities.Record;
import com.bbt.back.model.*;
import com.bbt.back.service.ProductService;
import com.bbt.back.service.RecordService;
import com.bbt.back.utils.CutWord;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 19:25
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/delete")
    private Object deleteProduct(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String deleteObjectStr = HttpServletRequestUtil.getString(request,"deleteObject");
        ObjectMapper mapper = new ObjectMapper();
        DeleteObject deleteObject = mapper.readValue(deleteObjectStr,DeleteObject.class);
        List<ProductLikeObject> likeObjects = deleteObject.getLikeObjects();
        for (ProductLikeObject likeObject : likeObjects){
            productService.deleteProduct(likeObject);
        }
        return resultEntity;
    }

    @RequestMapping("/list")
    private Object getList(HttpServletRequest request, Integer pageNum, Integer pageSize){
        ResultEntity resultEntity = new ResultEntity();
        int type = HttpServletRequestUtil.getInt(request,"productType");
        int library = HttpServletRequestUtil.getInt(request,"library");
        PageInfo<Object> pageInfo=productService.selectByTypeAndLibrary(type,library,pageNum,pageSize);
        PageInfoResult pageInfoResult=new PageInfoResult(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getPageNum(),pageInfo.getPageSize());
        if (pageInfo!=null){
            resultEntity.setMsg("获取成功");
            resultEntity.setData(pageInfoResult);
        } else{
            resultEntity.setMsg("获取失败");
        }
        return resultEntity;
    }

    @RequestMapping("/changeState")
    private Object changeState(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String productObjectStr = HttpServletRequestUtil.getString(request,"productObject");
        ObjectMapper mapper = new ObjectMapper();
        ProductObject productObject = mapper.readValue(productObjectStr,ProductObject.class);
        if (productService.changeState(productObject) == 0){
            resultEntity.setMsg("操作成功");
        } else {
            resultEntity.setMsg("操作失败");
        }
        return resultEntity;
    }

    @RequestMapping("/hotProduct")
    private Object getHotProduct(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        List<Object> products = productService.getHotProduct();
        if (products.isEmpty()){
            resultEntity.setMsg("获取热门产品失败");
        } else {
            resultEntity.setMsg("获取热门产品成功");
            resultEntity.setData(products);
        }
        return resultEntity;
    }

    @RequestMapping("/searchProduct")
    private Object searchProduct(HttpServletRequest request, Integer pageNum, Integer pageSize) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String searchObjectStr = HttpServletRequestUtil.getString(request,"searchObject");
        ObjectMapper mapper = new ObjectMapper();
        SearchObject searchObject = mapper.readValue(searchObjectStr,SearchObject.class);
        String searchStr = searchObject.getSearchStr();
        List<String> searchTokens = CutWord.cutWord(searchStr);
        StringBuilder searchToken = new StringBuilder();
        searchToken.append("%");
        for (String token : searchTokens){
            searchToken.append(token).append("%");
        }
        PageInfo<Object> pageInfo = productService.selectByToken(searchObject.getType(),searchToken.toString(),pageNum,pageSize);
        List<Object> products=pageInfo.getList();
        Long total=pageInfo.getTotal();
        Integer pageNum1=pageInfo.getPageNum();
        Integer pageSize1=pageInfo.getPageSize();
        SearchResult searchResult = new SearchResult(searchTokens,products,total,pageNum1,pageSize1);
        resultEntity.setMsg("搜索成功");
        resultEntity.setData(searchResult);
        return  resultEntity;
    }

    @RequestMapping("/likeProduct")
    private Object likeProduct(HttpServletRequest request) throws IOException{
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        String likeObjectStr = HttpServletRequestUtil.getString(request,"likeObject");
        ObjectMapper mapper = new ObjectMapper();
        ProductLikeObject likeObject = mapper.readValue(likeObjectStr,ProductLikeObject.class);
        if(productService.likeProduct(likeObject,userId)==1){
            resultEntity.setMsg("点赞成功");
        }else{
            resultEntity.setMsg("点赞取消");
        }
        return resultEntity;
    }

    @RequestMapping("/detail")
    private Object getDetail(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String likeObjectStr = HttpServletRequestUtil.getString(request,"likeObject");
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        ObjectMapper mapper = new ObjectMapper();
        ProductLikeObject likeObject = mapper.readValue(likeObjectStr,ProductLikeObject.class);
        Object object = productService.selectByProductId(likeObject);
        //插入足迹
        ProductResult product=productService.findByProductIdAndType(likeObject);
        Record record=new Record(userId,likeObject.getProductId(),likeObject.getType(),product.getProductName(),product.getProductPicture(),new Date());
        recordService.addRecord(record);
        resultEntity.setMsg("获取成功");
        resultEntity.setData(object);
        return resultEntity;
    }
}
