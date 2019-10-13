package com.bbt.back.service;

import com.bbt.back.entities.Collect;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectService {
    int countByUserId(int userId);

    List<Collect> selectAllByUserId(int userId);

    int addCollect(Collect collect);

    int deleteCollect(int collectId);
}
