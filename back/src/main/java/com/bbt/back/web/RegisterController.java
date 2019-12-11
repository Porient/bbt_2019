package com.bbt.back.web;

import com.bbt.back.entities.Admin;
import com.bbt.back.entities.User;
import com.bbt.back.enums.RegisterResultEnum;
import com.bbt.back.enums.SystemErrorEnum;
import com.bbt.back.exception.LoginException;
import com.bbt.back.exception.RegisterException;
import com.bbt.back.model.Common.Constant;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.RegisterService;
import com.bbt.back.service.UserService;
import com.bbt.back.utils.DateUtil;
import com.bbt.back.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 注册控制器类
 * @Author: Liu Bin
 * @Date: 2019/10/6 21:07
 */
@RestController
@RequestMapping("")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    private Object registerByUser(HttpServletRequest request) {
        //1.将前台获取的参数转换成User对象
        ResultEntity resultEntity=new ResultEntity();
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
        //2.进行注册,user是由是由前端传递过来的json字符串
        try {
            ResultEntity result = registerService.registerByUser(user);
            if (result.getCode().intValue() == RegisterResultEnum.SUCCESS.getCode().intValue()) {
                resultEntity.setData(result.getData());
            }
            resultEntity.setCode(result.getCode());
            resultEntity.setMsg(result.getMsg());
            return resultEntity;
        } catch (RegisterException e) {
            resultEntity.setCode(e.getCode());
            resultEntity.setMsg(e.getMessage());
            return resultEntity;
        }
    }

    /**
     * create by: Bin Liu
     * description: 发送邮件验证码
     * create time: 2019/5/24 14:44
     * @Param: null
     * @return
     */
    @PostMapping("/user/code")
    public Object sendVerifyCode(HttpServletRequest request){
        ResultEntity resultEntity=new ResultEntity();
        //1.将前台获取username和email参数
        String userEmail = HttpServletRequestUtil.getString(request, "userEmail");
        //2.发送验证码
        try {
            String verifyCode = userService.sendVerifyCode(userEmail);
            if (verifyCode!=null||!verifyCode.equals("")) {
                resultEntity.setData(verifyCode);
                resultEntity.setMsg( "发送验证码成功");
                resultEntity.setCode( 200);
            } else {
                resultEntity.setMsg( "发送验证码失败");
                resultEntity.setCode( 500);
            }
            //将验证码信息存入session中
            request.getSession().setAttribute("user_code_" + userEmail,verifyCode);
            request.getSession().setAttribute("user_codeTime_" + userEmail,new Date());
        } catch (LoginException ex) {
            resultEntity.setCode(ex.getCode());
            resultEntity.setMsg(ex.getMessage());
        }
        return resultEntity;
    }

}