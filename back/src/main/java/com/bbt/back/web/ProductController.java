package com.bbt.back.web;

import com.bbt.back.entities.Phone;
import com.bbt.back.model.*;
import com.bbt.back.service.ProductService;
import com.bbt.back.utils.CutWord;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping("/delete")
    private Object deleteProduct(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request,"productId");
        if (productService.deleteProduct(productId) == 0){
            resultEntity.setMsg("删除成功");
        } else {
            resultEntity.setMsg("删除失败");
        }
        return resultEntity;
    }

//    @RequestMapping("/preList")
//    private Object getPreList(HttpServletRequest request){
//        ResultEntity resultEntity = new ResultEntity();
//        ProductList productList = productService.selectAllPre();
//        resultEntity.setMsg("获取成功");
//        resultEntity.setData(productList);
//        return resultEntity;
//    }
//
//    @RequestMapping("formalList")
//    private Object getFormalList(HttpServletRequest request){
//        ResultEntity resultEntity = new ResultEntity();
//        ProductList productList = productService.selectAllFormal();
//        resultEntity.setMsg("获取成功");
//        resultEntity.setData(productList);
//        return resultEntity;
//    }

    @RequestMapping("List")
    private Object getList(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int type = HttpServletRequestUtil.getInt(request,"productType");
        if (productService.selectByType(type)!=null){
            resultEntity.setMsg("获取成功");
            resultEntity.setData(productService.selectByType(type));
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
    private Object searchProduct(HttpServletRequest request) throws IOException {
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
        List<Object> products = productService.selectByToken(searchObject.getType(),searchToken.toString());
        SearchResult searchResult = new SearchResult(searchTokens,products);
        resultEntity.setMsg("搜索成功");
        resultEntity.setData(searchResult);
        return  resultEntity;
    }

    @RequestMapping("/likeProduct")
    private Object likeProduct(HttpServletRequest request) throws IOException{
        ResultEntity resultEntity = new ResultEntity();
        String likeObjectStr = HttpServletRequestUtil.getString(request,"likeObject");
        ObjectMapper mapper = new ObjectMapper();
        LikeObject likeObject = mapper.readValue(likeObjectStr,LikeObject.class);
        productService.likeProduct(likeObject);
        return resultEntity;
    }

    @RequestMapping("/detail")
    private Object getDetail(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String likeObjectStr = HttpServletRequestUtil.getString(request,"likeObject");
        ObjectMapper mapper = new ObjectMapper();
        LikeObject likeObject = mapper.readValue(likeObjectStr,LikeObject.class);
        Object object = productService.selectByProductId(likeObject);
        resultEntity.setMsg("获取成功");
        resultEntity.setData(object);
        return resultEntity;
    }

}
