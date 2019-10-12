package com.bbt.back.service.impl;

import com.bbt.back.dao.CollectDao;
import com.bbt.back.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/11 18:06
 */
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public int countByUserId(int userId) {
        return collectDao.countByUserId(userId);
    }
}
