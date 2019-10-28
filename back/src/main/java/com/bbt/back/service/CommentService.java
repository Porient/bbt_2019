package com.bbt.back.service;

import com.bbt.back.entities.Comment;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 10:18
 */
@Component
public interface CommentService {
    /**
     * create by: Kobe
     * description:添加评论
     * create time: 12:07 2019/5/22
     * @param comment
     * @return int
     */
    int addComment(Comment comment);

    /**
     * create by: Kobe
     * description:删除评论
     * create time: 12:10 2019/5/22
     * @param commentId
     * @return int
     */
    int delComment(int commentId);

    /**
     * create by: Kobe
     * description:更新评论
     * create time: 12:13 2019/5/22
     * @param comment
     * @return
     */
    int updateComment(Comment comment);

    /**
     * create by: Kobe
     * description:根据id找评论接口
     * create time: 14:59 2019/5/22
     * @param commentId
     * @return
     */
    Comment findCommentById(int commentId);

    /**
     * create by: Bin Liu
     * description:获取评论列表
     * create time: 11:24 2019/6/4
     * @param
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Comment> selectAllByUserId(Integer userId, Integer pageNo, Integer pageSize);

    Comment getMostLikeComment(Integer userId);

    List<Comment> selectByUserId(Integer userId);

    int likeComment(int userId, int commentId);

    Integer findCommentNumByUserId(Integer userId);

    Integer rankByUserId(Integer userId);

    Integer sumByLikeNumLess(int i);

    Integer sumByLikeNumBetween(int i, int j);

    Integer sumByLikeNumMore(int i);
}
