package com.bbt.back.web;

import com.bbt.back.entities.Record;
import com.bbt.back.entities.User;
import com.bbt.back.enums.SystemErrorEnum;
import com.bbt.back.enums.UserResultEnum;
import com.bbt.back.exception.UserException;
import com.bbt.back.model.CollectPic;
import com.bbt.back.model.CommentPic;
import com.bbt.back.model.Common.Constant;
import com.bbt.back.model.RecordPic;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.CollectService;
import com.bbt.back.service.CommentService;
import com.bbt.back.service.RecordService;
import com.bbt.back.service.UserService;
import com.bbt.back.utils.DateUtil;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 10:18
 */

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private RecordService recordService;


    @PostMapping("/changePassword")
    private Object changePassword(HttpServletRequest request) {
        ResultEntity resultEntity=new ResultEntity();
        //获取user对应的json字符串
        String userStr = HttpServletRequestUtil.getString(request, "user");
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(userStr, User.class);
            //1.比较验证码是否一致或者超时
            String sessionCode = (String) request.getSession().getAttribute("user_code_" + user.getUserEmail());
            if (sessionCode == null) {
                resultEntity.setMsg("请先获取验证码");
                return resultEntity;
            }
            //获取前端传递过来的code参数
            String verifyCode = HttpServletRequestUtil.getString(request, "verifyCode");
            if (verifyCode != null && verifyCode.equals(sessionCode)) {
                //判断验证码是否过期
                Date sendTime = (Date) request.getSession().getAttribute("user_codeTime_" + user.getUserEmail());
                long seconds = DateUtil.getDifferenceSeconds(sendTime, new Date());
                if (seconds > Constant.OVERDUESECONDS) {
                    resultEntity.setMsg("验证码已过期");
                    return resultEntity;
                }
            } else {
                resultEntity.setMsg("验证码不正确");
                return resultEntity;
            }
        } catch (IOException e) {
            resultEntity.setMsg(SystemErrorEnum.SYSTEM_INNER_ERROR.getMsg());
            return resultEntity;
        }
        //2.调用userService更新用户密码信息
        try {
            ResultEntity result = userService.updateUser(user);
            if (result.getCode().equals(UserResultEnum.SUCCESS.getCode())) {
                resultEntity.setData(result.getData());
            }
            resultEntity.setCode(result.getCode());
            resultEntity.setMsg(result.getMsg());
            return resultEntity;
        } catch (UserException e) {
            resultEntity.setCode(e.getCode());
            resultEntity.setMsg(e.getMessage());
            return resultEntity;
        }
    }

    @PostMapping("/update")
    private Object update(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity=new ResultEntity();
        //获取user对应的json字符串
        String userStr = HttpServletRequestUtil.getString(request, "user");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userStr, User.class);
        try {
            ResultEntity userResult = userService.updateUser(user);
            resultEntity.setCode(userResult.getCode());
            resultEntity.setMsg(userResult.getMsg());
            return resultEntity;
        } catch (UserException ex) {
            resultEntity.setCode(ex.getCode());
            resultEntity.setMsg(ex.getMessage());
            return resultEntity;
        }
    }

    @RequestMapping("/getCommentPic")
    private Object getCommentPic(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity = new ResultEntity();
        Integer userId = HttpServletRequestUtil.getInt(request,"userId");
        Integer commentNum=commentService.findCommentNumByUserId(userId);
        Integer rank=commentService.rankByUserId(userId);
        Integer likeNum1=commentService.sumByLikeNumLess(50);
        Integer likeNum2=commentService.sumByLikeNumBetween(50,100);
        Integer likeNum3=commentService.sumByLikeNumBetween(100,200);
        Integer likeNum4=commentService.sumByLikeNumMore(200);
        CommentPic commentPic=new CommentPic(commentNum,rank,likeNum1,likeNum2,likeNum3,likeNum4);
        resultEntity.setData(commentPic);
        return resultEntity;
    }

    @RequestMapping("/getRecordPic")
    private Object getRecordPic(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity = new ResultEntity();
        Integer userId = HttpServletRequestUtil.getInt(request,"userId");
        Integer recordNum=recordService.findRecordNumByUserId(userId);
        Integer computerNum=recordService.findComputerNumByUserId(userId);
        Integer phoneNum=recordService.findPhoneNumByUserId(userId);
        HashMap<String,Integer> recordMap=recordService.sumByUserId(userId);
        HashMap<String,Integer> timeMap=recordService.sumByTime();
        RecordPic recordPic=new RecordPic(recordNum,computerNum,phoneNum,recordMap,timeMap);
        resultEntity.setData(recordPic);
        return resultEntity;
    }

    @RequestMapping("/getCollectPic")
    private Object getCollectPic(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity = new ResultEntity();
        Integer userId = HttpServletRequestUtil.getInt(request,"userId");
        Integer collectNum=collectService.findCollectNumByUserId(userId);
        Integer rank=collectService.rankByUserId(userId);
        HashMap<String,Integer> map=collectService.sumByUserId(userId);
        CollectPic collectPic=new CollectPic(collectNum,rank,map);
        resultEntity.setData(collectPic);
        return resultEntity;
    }
}
