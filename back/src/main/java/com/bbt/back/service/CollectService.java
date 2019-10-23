package com.bbt.back.service;

import com.bbt.back.entities.Collect;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectService {
    int countByUserId(int userId);

    PageInfo<Collect> selectAllByUserId(int userId, Integer pageNum, Integer pageSize);

    int addCollect(Collect collect);

    int deleteCollect(int collectId);
}
