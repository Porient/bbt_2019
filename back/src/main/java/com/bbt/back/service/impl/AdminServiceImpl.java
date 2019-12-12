package com.bbt.back.service.impl;

import com.bbt.back.dao.AdminDao;
import com.bbt.back.entities.Admin;
import com.bbt.back.model.AdminDto;
import com.bbt.back.model.ResultEntity;
import com.bbt.back.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 15:48
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdminById(int id) {
        return adminDao.findAdminById(id);
    }

    @Override
    public Admin getAdmin(String adminEmail, String password) {
        return adminDao.findByAdminEmailAndPassword(adminEmail,password);
    }

    @Override
    public int deleteAdmin(Integer id) {
        return adminDao.deleteAdmin(id);
    }
}
