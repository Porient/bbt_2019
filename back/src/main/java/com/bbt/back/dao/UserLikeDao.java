package com.bbt.back.dao;

import com.bbt.back.entities.UserLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/23 0023 20:00
 */
@Component
public interface UserLikeDao {
    int insertUserLike(UserLike userLike);

    UserLike findByProductIdAndType(@Param("userId")int userId, @Param("productId")int productId, @Param("productType")int productType);


    int deleteUserLike(@Param("userId")int userId,@Param("productId") int productId, @Param("productType")int productType);
}
