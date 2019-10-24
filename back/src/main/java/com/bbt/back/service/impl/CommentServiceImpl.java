package com.bbt.back.service.impl;

import com.bbt.back.dao.*;
import com.bbt.back.entities.Comment;
import com.bbt.back.entities.CommentLike;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.User;
import com.bbt.back.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/7 0007 10:19
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentLikeDao commentLikeDao;

    @Override
    public int addComment(Comment comment) {
        comment.setDate(new Date());
        return commentDao.insertComment(comment);
    }

    @Override
    public int delComment(int commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Override
    public int updateComment(Comment comment) {
        return 0;
    }

    @Override
    public Comment findCommentById(int commentId) {
        return commentDao.findCommentById(commentId);
    }

    @Override
    public PageInfo<Comment> selectAllByUserId(Integer userId, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == -1 ? 1 : pageNo;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Comment> list = commentDao.getCommentListByUserId(userId);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Comment getMostLikeComment(Integer userId) {
        return commentDao.getMostLikeComment(userId);
    }

    @Override
    public List<Comment> selectByUserId(Integer userId) {
        return commentDao.selectByUserId(userId);
    }

    @Override
    public int getLikeCount(Integer userId) {
        return commentDao.getLikeCount(userId);
    }

    @Override
    public int likeComment(int userId, int commentId) {
        CommentLike commentLike=commentLikeDao.findByUserIdAndCommentId(userId,commentId);
        Comment comment=commentDao.findCommentById(commentId);
        int oldNum=comment.getLikeNum();
        if (commentLike==null){
            commentLike=new CommentLike(userId,commentId,new Date());
            commentLikeDao.insertCommentLike(commentLike);
        }else {
            commentLikeDao.deleteCommentLike(userId,commentId);
            comment.setLikeNum(oldNum-1);
            commentDao.updateComment(comment);
            return -1;
        }
        comment.setLikeNum(oldNum+1);
        commentDao.updateComment(comment);
        return 1;
    }
}
