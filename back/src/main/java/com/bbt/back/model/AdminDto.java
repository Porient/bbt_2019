package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/12 0012 10:43
 */
public class AdminDto  implements Serializable {
    private Integer adminId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public AdminDto(Integer adminId) {
        this.adminId = adminId;
    }
}
