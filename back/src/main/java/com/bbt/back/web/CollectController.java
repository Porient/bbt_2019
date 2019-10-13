package com.bbt.back.web;

import com.bbt.back.entities.Collect;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.CollectService;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/12 16:43
 */
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping("/collects")
    private Object getCollects(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request,"userId");
        List<Collect> collects = collectService.selectAllByUserId(userId);
        resultEntity.setMsg("获取收藏列表成功");
        resultEntity.setData(collects);
        return resultEntity;
    }

    @RequestMapping("/addCollect")
    private Object addCollect(HttpServletRequest request) throws IOException {
        ResultEntity resultEntity = new ResultEntity();
        String collectStr = HttpServletRequestUtil.getString(request,"collectStr");
        ObjectMapper mapper = new ObjectMapper();
        Collect collect = mapper.readValue(collectStr,Collect.class);
        if (collectService.addCollect(collect) == 0){
            resultEntity.setMsg("添加收藏成功");
        } else {
            resultEntity.setMsg("添加收藏失败");
        }
        return resultEntity;
    }

    @RequestMapping("/delete")
    private Object deleteCollect(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int collectId = HttpServletRequestUtil.getInt(request,"collectId");
        if (collectService.deleteCollect(collectId) == 0){
            resultEntity.setMsg("删除收藏成功");
        } else{
            resultEntity.setMsg("删除收藏成功");
        }
        return  resultEntity;
    }
}
