package com.bbt.back.dao;

import com.bbt.back.entities.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 管理员Dao
 * @Author: Liu Bin
 * @Date: 2019/10/6 16:27
 */
@Component
public interface AdminDao {
    int deleteAdmin(Integer id);

    int insertAdmin(Admin admin);

    Admin findAdminById(Integer id);

    int updateAdmin(Admin admin);

    Admin findByAdminEmail(String adminEmail);

    Admin findByAdminEmailAndPassword(@Param("adminEmail")String adminEmail, @Param("password")String password);
}
