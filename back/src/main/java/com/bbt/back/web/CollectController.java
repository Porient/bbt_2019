package com.bbt.back.web;

import com.bbt.back.entities.Collect;
import com.bbt.back.model.PageInfoResult;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.CollectService;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/12 16:43
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping("/list")
    private Object getCollects(HttpServletRequest request, Integer pageNum, Integer pageSize){
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request,"userId");
        PageInfo<Collect> pageInfo = collectService.selectAllByUserId(userId,pageNum,pageSize);
        PageInfoResult pageInfoResult=new PageInfoResult(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getPageNum(),pageInfo.getPageSize());
        resultEntity.setMsg("获取收藏列表成功");
        resultEntity.setCode(200);
        resultEntity.setData(pageInfoResult);
        return resultEntity;
    }

    @PostMapping("/add")
    private Object addCollect(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String collectStr = HttpServletRequestUtil.getString(request,"collectStr");
        ObjectMapper mapper = new ObjectMapper();
        Collect collect = mapper.readValue(collectStr,Collect.class);
        if (collectService.addCollect(collect) == 0){
            resultEntity.setMsg("添加收藏成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("添加收藏失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @DeleteMapping("/delete")
    private Object deleteCollect(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int collectId = HttpServletRequestUtil.getInt(request,"collectId");
        if (collectService.deleteCollect(collectId) == 0){
            resultEntity.setMsg("删除收藏成功");
            resultEntity.setCode(200);
        } else{
            resultEntity.setMsg("删除收藏失败");
            resultEntity.setCode(500);
        }
        return  resultEntity;
    }
}
