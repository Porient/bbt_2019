package com.bbt.back.service.impl;

import com.bbt.back.dao.UserDao;
import com.bbt.back.entities.User;
import com.bbt.back.enums.SystemErrorEnum;
import com.bbt.back.enums.UserResultEnum;
import com.bbt.back.exception.CustomException;
import com.bbt.back.exception.UserException;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.UserService;
import com.bbt.back.utils.MailUtil;
import com.bbt.back.utils.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 21:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public User findByUserEmail(String userEmail) {
        return userDao.findByUserEmail(userEmail);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public int insert(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public ResultEntity updateUser(User user) {
        ResultEntity userResult = new ResultEntity();
        if (user == null) {
            userResult.setErrCode(UserResultEnum.INPUT_NULL.getCode());
            userResult.setMsg(UserResultEnum.INPUT_NULL.getMsg());
            return userResult;
        }
        try {
            //调用dao层更新数据
            int result = userDao.updateUser(user);
            if (result == 1){
                userResult.setErrCode(UserResultEnum.SUCCESS.getCode());
                userResult.setMsg(UserResultEnum.SUCCESS.getMsg());
            } else {
                userResult.setErrCode(UserResultEnum.FAILD.getCode());
                userResult.setMsg(UserResultEnum.FAILD.getMsg());
            }
            return userResult;
        }catch (Exception e){
            throw new UserException(SystemErrorEnum.SYSTEM_INNER_ERROR.getMsg(), SystemErrorEnum.SYSTEM_INNER_ERROR.getCode());
        }
    }

    @Override
    public int deleteById(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public PageInfo<User> getUserList(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == -1 ? 1 : pageNo;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<User> list = userDao.getUserListByParams();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public String sendVerifyCode(String email) {
        //1.生成验证码
        String checkCode = RandomUtil.getRandomVerCode();
        String message = "您的注册验证码为：" + checkCode;
        //3.发送邮件
        try {
            mailUtil.sendSimpleMail(email, "注册验证码", message);
        } catch (Exception e) {
            throw new CustomException("发送邮箱验证码失败");
        }
        return checkCode;
    }
}
