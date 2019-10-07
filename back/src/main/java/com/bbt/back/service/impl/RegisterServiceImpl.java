package com.bbt.back.service.impl;

import com.bbt.back.dao.AdminDao;
import com.bbt.back.dao.UserDao;
import com.bbt.back.entities.Admin;
import com.bbt.back.entities.User;
import com.bbt.back.enums.RegisterResultEnum;
import com.bbt.back.exception.RegisterException;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.RegisterService;
import com.bbt.back.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 21:30
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private AdminDao adminDao;

    @Transactional
    @Override
    public ResultEntity registerByUser(User user) {
        ResultEntity result = new ResultEntity();
        //1.判断输入是否为空
        if (user == null ) {
            result.setErrCode(RegisterResultEnum.INPUT_NULL.getCode());
            result.setMsg(RegisterResultEnum.INPUT_NULL.getMsg());
            return result;
        }
        try {
            //1.判断用户是否存在数据库中，如果不存在
            User oldUser = userDao.findByUserEmail(user.getUserEmail());
            if (oldUser != null) {
                // 用户已经存在
                result.setErrCode(RegisterResultEnum.USER_FAILD.getCode());
                result.setMsg(RegisterResultEnum.USER_FAILD.getMsg());
            }
            //3.插入用户信息，其实这里就是为了简便，不再捕获系统可能出现的异常，更复杂的逻辑是先判断用户是否存在，再去判断是否出现系统异常
            if (userDao.insertUser(user) == 1) {
                result.setErrCode(RegisterResultEnum.SUCCESS.getCode());
                result.setMsg(RegisterResultEnum.SUCCESS.getMsg());
            } else {
                result.setErrCode(RegisterResultEnum.USER_FAILD.getCode());
                result.setMsg(RegisterResultEnum.USER_FAILD.getMsg());
            }
            return result;
        } catch (Exception ex) {
            throw new RegisterException(RegisterResultEnum.USER_EXIST.getCode(), RegisterResultEnum.USER_EXIST.getMsg());
        }
    }

    @Transactional
    @Override
    public ResultEntity registerByAdmin(Admin admin) {
        ResultEntity result = new ResultEntity();
        //1.判断输入是否为空
        if (admin == null ) {
            result.setErrCode(RegisterResultEnum.INPUT_NULL.getCode());
            result.setMsg(RegisterResultEnum.INPUT_NULL.getMsg());
            return result;
        }
        try {
            //3.插入管理员信息
            if (adminDao.insertAdmin(admin) == 1) {
                result.setErrCode(RegisterResultEnum.APPLY_SUCCESS.getCode());
                result.setMsg(RegisterResultEnum.APPLY_SUCCESS.getMsg());
            } else {
                result.setErrCode(RegisterResultEnum.ADMIN_FAILD.getCode());
                result.setMsg(RegisterResultEnum.ADMIN_FAILD.getMsg());
            }
            return result;
        } catch (Exception ex) {
            throw new RegisterException(RegisterResultEnum.APPLY_REPEAT.getCode(), RegisterResultEnum.APPLY_REPEAT.getMsg());
        }
    }
}
