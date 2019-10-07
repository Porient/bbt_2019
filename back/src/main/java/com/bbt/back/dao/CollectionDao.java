package com.bbt.back.dao;

import com.bbt.back.entities.Collection;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:38
 */
public interface CollectionDao {
    int deleteCollection(Integer id);

    int insertCollection(Collection collection);

    Collection findCollectionById(Integer id);

    int updateCollection(Collection collection);

    List<Collection> selectAll();
}
