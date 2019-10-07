package com.bbt.back.service;

import com.bbt.back.entities.Admin;
import com.bbt.back.model.ResultEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 15:39
 */
public interface AdminService {
    /**
     * create by: Bin Liu
     * description: 根据id查管理员信息
     * create time: 2019/5/24 20:54
     * @Param: null
     * @return
     */
    Admin getAdminById(int id);

    /**
     * create by: Bin Liu
     * description: 根据传入的姓名和密码查找管理员
     * create time: 2019/5/24 20:57
     * @Param: null
     * @return
     */
    Admin getAdmin(@Param("adminEmail") String adminEmail, @Param("password")String password);

    /**
     * create by: Bin Liu
     * description: 根据传入的管理员信息更新管理员
     * create time: 2019/5/24 20:57
     * @Param: null
     * @return
     */
    ResultEntity updateAdmin(Admin admin);

    /**
     * create by: Bin Liu
     * description: 删除管理员信息
     * create time: 2019/5/24 20:58
     * @Param: null
     * @return
     */
    int deleteAdmin(Integer id);
}
