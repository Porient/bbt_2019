package com.bbt.back.service;

import com.bbt.back.entities.User;
import com.bbt.back.model.ResultEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 21:18
 */
public interface UserService {
    /**
     * create by: Bin Liu
     * description: 通过userEmail查找用户信息
     * create time: 2019/5/24 14:53
     * @Param: null
     * @return
     */
    public User findByUserEmail(String userEmail);

    User getUserById(Integer userId);

    //PageInfo<User> selectAll(Integer pageNo, Integer pageSize);

    int insert(User user);

    ResultEntity updateUser(User user);

    int deleteById(Integer id);

    /**
     * create by: Bin Liu
     * description: 获取用户列表
     * create time: 2019/5/24 23:09
     * @Param: null
     * @return
     */
    PageInfo<User> getUserList(Integer pageNo, Integer pageSize);

    /**
     * create by: Bin Liu
     * description: 发送邮件验证码
     * create time: 2019/5/24 22:38
     * @Param: null
     * @return
     */
    String sendVerifyCode(String email);
}
