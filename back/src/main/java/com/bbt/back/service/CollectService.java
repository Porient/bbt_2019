package com.bbt.back.service;

import com.bbt.back.entities.Collect;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public interface CollectService {
    PageInfo<Collect> selectAllByUserId(int userId, Integer pageNum, Integer pageSize);

    int addCollect(Collect collect);

    int deleteCollect(int collectId);

    Integer findCollectNumByUserId(Integer userId);

    Integer rankByUserId(Integer userId);

    HashMap<String,Integer> sumByUserId(Integer userId);
}
