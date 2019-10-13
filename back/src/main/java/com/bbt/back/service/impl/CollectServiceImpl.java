package com.bbt.back.service.impl;

import com.bbt.back.dao.CollectDao;
import com.bbt.back.entities.Collect;
import com.bbt.back.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Override
    public List<Collect> selectAllByUserId(int userId) {
        return collectDao.selectByUserId(userId);
    }

    @Override
    public int addCollect(Collect collect) {
        return collectDao.insertCollect(collect);
    }

    @Override
    public int deleteCollect(int collectId) {
        return collectDao.deleteCollect(collectId);
    }
}
