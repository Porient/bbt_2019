package com.bbt.back.web;

import com.bbt.back.entities.Collect;
import com.bbt.back.entities.Comment;
import com.bbt.back.entities.User;
import com.bbt.back.enums.SystemErrorEnum;
import com.bbt.back.enums.UserResultEnum;
import com.bbt.back.exception.UserException;
import com.bbt.back.model.CommentAbbr;
import com.bbt.back.model.Common.Constant;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.CollectService;
import com.bbt.back.service.CommentService;
import com.bbt.back.service.UserService;
import com.bbt.back.utils.DateUtil;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
            if (result.getErrCode().equals(UserResultEnum.SUCCESS.getCode())) {
                resultEntity.setData(result.getData());
            }
            resultEntity.setErrCode(result.getErrCode());
            resultEntity.setMsg(result.getMsg());
            return resultEntity;
        } catch (UserException e) {
            resultEntity.setErrCode(e.getCode());
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
            resultEntity.setErrCode(userResult.getErrCode());
            resultEntity.setMsg(userResult.getMsg());
            return resultEntity;
        } catch (UserException ex) {
            resultEntity.setErrCode(ex.getCode());
            resultEntity.setMsg(ex.getMessage());
            return resultEntity;
        }
    }

    @RequestMapping("/getCommentAbbr")
    private Object getCommentAbbr(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity = new ResultEntity();
        Integer userId = HttpServletRequestUtil.getInt(request,"userId");
        List<Comment> comments = commentService.selectByUserId(userId);
        resultEntity.setMsg("获取成功");
        if (comments.size() == 0){
            resultEntity.setData("你是一个新用户哦");
        } else {
            int commentCount = comments.size();
            int likeCount = commentService.getLikeCount(userId);
            Comment comment = commentService.getMostLikeComment(userId);
            CommentAbbr commentAbbr = new CommentAbbr(commentCount,likeCount,comment);
            resultEntity.setData(commentAbbr);
        }
        return resultEntity;
    }

    @RequestMapping("/collectCount")
    private Object getCollectCount(HttpServletRequest request) throws Exception{
        ResultEntity resultEntity = new ResultEntity();
        int userId = HttpServletRequestUtil.getInt(request,"userId");
        int collectCount = collectService.countByUserId(userId);
        resultEntity.setMsg("获取收藏数目成功");
        resultEntity.setData(collectCount);
        return resultEntity;
    }

}
