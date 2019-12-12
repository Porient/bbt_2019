package com.bbt.back.service.impl;

import com.bbt.back.dao.AdminDao;
import com.bbt.back.dao.UserDao;
import com.bbt.back.entities.Admin;
import com.bbt.back.entities.User;
import com.bbt.back.enums.LoginResultEnum;
import com.bbt.back.enums.SystemErrorEnum;
import com.bbt.back.exception.LoginException;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 21:30
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public ResultEntity loginByUser(String userEmail, String password) {
        ResultEntity loginResult = new ResultEntity();
        if ( password == null){
            loginResult.setCode(LoginResultEnum.INPUT_NULL.getCode());
            loginResult.setMsg(LoginResultEnum.INPUT_NULL.getMsg());
            return loginResult;
        }
        try{
            User u = userDao.findByUserEmailAndPassword(userEmail,password);
            //用户是否存在
            if (u == null){
                loginResult.setCode(LoginResultEnum.NOT_MATCH.getCode());
                loginResult.setMsg(LoginResultEnum.NOT_MATCH.getMsg());
            }  else{
                loginResult.setCode(LoginResultEnum.SUCCESS.getCode());
                loginResult.setMsg(LoginResultEnum.SUCCESS.getMsg());
                loginResult.setData(u);
            }
            return loginResult;
        }catch (Exception e){
            throw new LoginException(SystemErrorEnum.SYSTEM_INNER_ERROR.getMsg(),SystemErrorEnum.SYSTEM_INNER_ERROR.getCode());
        }
    }

    @Override
    public ResultEntity loginByAdmin(String adminEmail, String password) {
        ResultEntity loginResult = new ResultEntity();
        if (password == null){
            loginResult.setCode(LoginResultEnum.INPUT_NULL.getCode());
            loginResult.setMsg(LoginResultEnum.INPUT_NULL.getMsg());
            return loginResult;
        }
        try{
            Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail,password);
            if (admin== null) {
                loginResult.setCode(LoginResultEnum.NOT_MATCH.getCode());
                loginResult.setMsg(LoginResultEnum.NOT_MATCH.getMsg());
            } else {
                loginResult.setCode(LoginResultEnum.SUCCESS.getCode());
                loginResult.setMsg(LoginResultEnum.SUCCESS.getMsg());
                loginResult.setData(admin);
            }
            return loginResult;
        }catch (Exception e){
            throw new LoginException(SystemErrorEnum.SYSTEM_INNER_ERROR.getMsg(),SystemErrorEnum.SYSTEM_INNER_ERROR.getCode());
        }
    }
}
