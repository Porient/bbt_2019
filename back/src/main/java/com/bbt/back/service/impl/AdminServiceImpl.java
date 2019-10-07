package com.bbt.back.service.impl;

import com.bbt.back.entities.Admin;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 15:48
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public Admin getAdminById(int id) {
        return null;
    }

    @Override
    public Admin getAdmin(String adminEmail, String password) {
        return null;
    }

    @Override
    public ResultEntity updateAdmin(Admin admin) {
        return null;
    }

    @Override
    public int deleteAdmin(Integer id) {
        return 0;
    }
}
