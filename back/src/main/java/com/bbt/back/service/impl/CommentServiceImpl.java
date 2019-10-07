package com.bbt.back.service.impl;

import com.bbt.back.dao.CommentDao;
import com.bbt.back.dao.UserDao;
import com.bbt.back.entities.Comment;
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
    private UserDao userDao;

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
    public PageInfo<Comment> selectAll(Integer productId, Integer pageNo, Integer pageSize) {
        /*pageNo = pageNo == -1 ? 1 : pageNo;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Comment> list = commentDao.getCommentListByBookId(productId);
        for(Comment comment:list){
            comment.setUser(userDao.findUserById(comment.getFromUid()));
            comment.setBookInfo(bookInfoDao.findBookInfoById(comment.getBookId()));
        }
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;*/
        return null;
    }

    @Override
    public Comment findCommentById(int commentId) {
        return commentDao.findCommentById(commentId);
    }

    @Override
    public PageInfo<Comment> selectAllByUserId(Integer userId, Integer pageNo, Integer pageSize) {
        /*pageNo = pageNo == -1 ? 1 : pageNo;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Comment> list = commentDao.getCommentListByUserId(userId);
        for(Comment comment:list){
            User user=userDao.findUserById(comment.getUserId());
            comment.setUser(user);
            BookInfo bookInfo=bookInfoDao.findBookInfoById(comment.getBookId());
            comment.setBookInfo(bookInfo);
        }
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;*/
        return null;
    }
}
