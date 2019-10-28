package com.bbt.back.dao;

import com.bbt.back.entities.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 16:41
 */

@Component
public interface CommentDao {
    int deleteComment(Integer commentId);

    int insertComment(Comment comment);

    Comment findCommentById(Integer commentId);

    List<Comment> selectAll();

    int updateComment(Comment comment);

    List<Comment> getCommentListByProductId(Integer productId);

    List<Comment> getCommentListByUserId(Integer userId);

    Comment getMostLikeComment(Integer userId);

    List<Comment> selectByUserId(Integer userId);

    int getLikeCount(Integer userId);

    Integer findCommentNumByUserId(Integer userId);

    List<HashMap<Integer,Object>> sumByUserIdList();

    Integer sumByLikeNumLess(Integer i);

    Integer sumByLikeNumBetween(@Param("i") Integer i,@Param("j") Integer j);

    Integer sumByLikeNumMore(Integer i);
}
