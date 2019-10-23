package com.bbt.back.dao;

import com.bbt.back.entities.CommentLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/23 0023 20:00
 */
@Component
public interface CommentLikeDao {
    int insertCommentLike(CommentLike commentLike);

    CommentLike findByUserIdAndCommentId(@Param("userId")int userId,@Param("commentId") int commentId);
}
