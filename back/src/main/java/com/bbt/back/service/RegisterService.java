package com.bbt.back.service;

import com.bbt.back.entities.Admin;
import com.bbt.back.entities.User;
import com.bbt.back.model.ResultEntity;

/**
 * @Description: 注册逻辑接口
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 21:08
 */
public interface RegisterService {
    /**
     * create by: Bin Liu
     * description: 用户注册
     * create time: 2019/5/24 21:19
     * @Param: null
     * @return
     */
    ResultEntity registerByUser(User user);

    /**
     * @Author: Liu Bin
     * @Date: 2019/10/6  21:09
     * @Description: 管理员申请
     * @Param:
     * @Rerurn:
     */
    ResultEntity registerByAdmin(Admin admin);
}
