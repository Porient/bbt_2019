package com.bbt.back.dao;

import com.bbt.back.entities.Collect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:38
 */
@Component
public interface CollectDao {
    int deleteCollect(Integer collectId);

    int insertCollect(Collect collect);

    Collect findCollectById(Integer collectId);

    int updateCollect(Collect collect);

    List<Collect> selectAll();

    List<Collect> selectByUserId(int userId);

    int countByUserId(int userId);
}
