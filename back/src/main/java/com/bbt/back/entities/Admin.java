package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description: 管理员实体类
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 15:17
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer adminId;
    private String adminEmail;
    private String password;

    public Admin() {

    }

    public Admin(Integer adminId, String adminEmail, String password) {
        this.adminId = adminId;
        this.adminEmail = adminEmail;
        this.password = password;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminEmail='" + adminEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
