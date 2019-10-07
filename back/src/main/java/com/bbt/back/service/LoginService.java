package com.bbt.back.service;

import com.bbt.back.model.ResultEntity;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 21:08
 */
public interface LoginService {
    /**
     * create by: Bin Liu
     * description: 用户登陆
     * create time: 2019/5/24 20:52
     * @Param: null
     * @return
     */
    ResultEntity loginByUser(String userEmail, String password);

    /**
     * create by: Bin Liu
     * description: 管理员登陆
     * create time: 2019/5/24 20:53
     * @Param: null
     * @return
     */
    ResultEntity loginByAdmin(String adminEmail, String password);
}
