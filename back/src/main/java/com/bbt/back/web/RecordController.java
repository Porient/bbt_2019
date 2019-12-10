package com.bbt.back.web;

import com.bbt.back.entities.Record;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.RecordService;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 11:06
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping("/list")
    private Object getRecordList(HttpServletRequest request,Integer pageNo, Integer pageSize){
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request,"userId");
        PageInfo<Record> recordPageInfo = recordService.selectAllByUserId(userId,pageNo,pageSize);
        resultEntity.setMsg("获取成功");
        resultEntity.setCode(200);
        resultEntity.setData(recordPageInfo);
        return resultEntity;
    }

    @RequestMapping("/delete")
    private Object deleteRecord(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int recordId = HttpServletRequestUtil.getInt(request,"recordId");
        if (recordService.deleteById(recordId) == 0){
            resultEntity.setMsg("删除成功");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("删除失败");
            resultEntity.setCode(500);
        }
        return resultEntity;
    }

    @RequestMapping("/detail")
    private Object getRecordDetail(HttpServletRequest request){
        ResultEntity resultEntity = new ResultEntity();
        int recordId = HttpServletRequestUtil.getInt(request,"record");
        Record record = recordService.findById(recordId);
        resultEntity.setMsg("获取成功");
        resultEntity.setCode(200);
        resultEntity.setData(record);
        return resultEntity;
    }

}
