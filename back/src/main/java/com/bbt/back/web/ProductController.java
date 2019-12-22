package com.bbt.back.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bbt.back.entities.Record;
import com.bbt.back.model.*;
import com.bbt.back.service.ProductService;
import com.bbt.back.service.RecordService;
import com.bbt.back.utils.CutWord;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 19:25
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RecordService recordService;

    @DeleteMapping("/delete")
    private Object deleteProduct(@RequestBody DeleteObject deleteObject) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("deleteObject",deleteObject);
        List<ProductLikeObject> likeObjects = deleteObject.getLikeObject();
        int deleteNum=0;
        int deleteAim=likeObjects.size();//总目标
        for (ProductLikeObject likeObject : likeObjects){
            deleteNum+=productService.deleteProduct(likeObject);
        }
        if(deleteNum==0){
            resultEntity.setMsg("全部删除不成功");
            resultEntity.setCode(500);
            return resultEntity;
        }else if(deleteNum<deleteAim&&deleteNum>0){
            resultEntity.setMsg("部分删除成功，部分删除不成功");
            resultEntity.setCode(500);
            return resultEntity;
        }
        resultEntity.setMsg("全部删除成功");
        resultEntity.setCode(200);
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
            resultEntity.setCode(200);
            resultEntity.setData(pageInfoResult);
        } else{
            resultEntity.setMsg("获取失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/changeState")
    private Object changeState(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String productObjectStr = HttpServletRequestUtil.getString(request,"productObject");
        ObjectMapper mapper = new ObjectMapper();
        ProductObject productObject = mapper.readValue(productObjectStr,ProductObject.class);
        if (productService.changeState(productObject) == 1){
            resultEntity.setMsg("操作成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("操作失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/getBasicInfo")
    private Object getBasicInfo(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request, "productId");
        String result=productService.getBasicInfo(productId);
        JSONObject basicInfo=JSON.parseObject(result);
        if (basicInfo != null){
            resultEntity.setData(basicInfo);
            resultEntity.setMsg("生成basicInfo成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("生成basicInfo失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/getStatisticInfo")
    private Object getStatisticInfo(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request, "productId");
        String result=productService.getStatisticInfo(productId);
        result = result.substring(0,result.length() - 1);
        result=result+", 'comment_wordcloud_path': './wordcloud/comment_cloud/"+productId+".png'}";
        JSONObject statisticInfo=JSON.parseObject(result);
        if (statisticInfo != null){
            resultEntity.setData(statisticInfo);
            resultEntity.setMsg("生成StatisticInfo成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("生成StatisticInfo失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/getCompareInfo")
    private Object getCompareInfo(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request, "productId");
        String result=productService.getCompareInfo(productId);
        JSONObject compareInfo=JSON.parseObject(result);
        if (compareInfo != null){
            resultEntity.setData(compareInfo);
            resultEntity.setMsg("生成CompareInfo成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("生成CompareInfo失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/getCommentInfo")
    private Object getCommentInfo(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request, "productId");
        String result=productService.getCommentInfo(productId);
        JSONArray commentInfo=JSON.parseArray(result);
        if (commentInfo != null){
            resultEntity.setData(commentInfo);
            resultEntity.setMsg("生成CommentInfo成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("生成CommentInfo失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/getMiningInfo")
    private Object getMiningInfo(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int productId = HttpServletRequestUtil.getInt(request, "productId");
        String result=productService.getMiningInfo(productId);
        JSONObject miningInfo=JSON.parseObject(result);
        if (miningInfo != null){
            resultEntity.setData(miningInfo);
            resultEntity.setMsg("生成MiningInfo成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("生成MiningInfo失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/hotProduct")
    private Object getHotProduct(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        List<Object> products = productService.getHotProduct();
        if (products.isEmpty()){
            resultEntity.setMsg("获取热门产品失败");
            resultEntity.setCode(500);
        } else {
            resultEntity.setMsg("获取热门产品成功");
            resultEntity.setData(products);
        }
        return resultEntity;
    }

    @RequestMapping("/recommendProduct")
    private Object getRecommendProduct(HttpServletRequest request){
        int userId = HttpServletRequestUtil.getInt(request, "userId");
        ResultEntity resultEntity = new ResultEntity();
        String result = productService.getRecommendProduct(userId);
        JSONObject products=JSON.parseObject(result);
        if (products.isEmpty()){
            resultEntity.setMsg("获取推荐产品失败");
            resultEntity.setCode(500);
        } else {
            resultEntity.setMsg("获取推荐产品成功");
            resultEntity.setCode(200);
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
        for (String token : searchTokens){
            searchToken.append(token).append("%");
        }
        PageInfo<Object> pageInfo = productService.selectByToken(searchObject.getType(),searchToken.toString(),pageNum,pageSize);
        List<Object> products=pageInfo.getList();
        Long total=pageInfo.getTotal();
        Integer pageNum1=pageInfo.getPageNum();
        Integer pageSize1=pageInfo.getPageSize();
        SearchResult searchResult = new SearchResult(searchTokens,products,total,pageNum1,pageSize1);
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
            resultEntity.setCode(200);
        }else{
            resultEntity.setMsg("点赞取消");
            resultEntity.setCode(500);
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
        resultEntity.setCode(200);
        resultEntity.setData(object);
        return resultEntity;
    }
}
