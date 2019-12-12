package com.bbt.back.service.impl;

import com.bbt.back.dao.*;
import com.bbt.back.entities.Comment;
import com.bbt.back.entities.CommentLike;
import com.bbt.back.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
        comment.setLikeNum(1);
        return commentDao.insertComment(comment);
    }

    @Override
    public int delComment(int commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
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
    public int likeComment(int userId, int commentId) {
        CommentLike commentLike=commentLikeDao.findByUserIdAndCommentId(userId,commentId);
        Comment comment=commentDao.findCommentById(commentId);
        int oldNum=comment.getLikeNum();
        if (commentLike==null){

            commentLike=new CommentLike(userId,commentId, new Timestamp(new Date().getTime()));
            commentLikeDao.insertCommentLike(commentLike);
        }else {
            commentLikeDao.deleteCommentLike(userId,commentId);
            comment.setLikeNum(oldNum-1);
            commentDao.updateComment(comment);
            return -1;
        }
        comment.setLikeNum(oldNum+1);
        return commentDao.updateComment(comment);
    }

    @Override
    public Integer findCommentNumByUserId(Integer userId) {
        return commentDao.findCommentNumByUserId(userId);
    }

    @Override
    public Integer rankByUserId(Integer userId) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer rank=-1;
        List<HashMap<Integer, Object>> list = commentDao.sumByUserIdList();
        if (list != null && !list.isEmpty()) {
            for (HashMap<Integer, Object> map1 : list) {
                Integer key = null;
                Integer value = null;
                for (Map.Entry<Integer, Object> entry : map1.entrySet()) {
                    if ("key".equals(entry.getKey())) {
                        key = (Integer) entry.getValue();
                    } else if ("value".equals(entry.getKey())) {
                        value = (Integer) entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }

        List<Map.Entry<Integer, Integer>> sortList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < sortList.size(); i++) {
            if(sortList.get(i).getKey()==userId){
                rank=i+1;
                break;
            }
        }
        return rank;
    }

    @Override
    public Integer sumByLikeNumLess(int i) {
        return commentDao.sumByLikeNumLess(i);
    }

    @Override
    public Integer sumByLikeNumBetween(int i, int j) {
        return commentDao.sumByLikeNumBetween(i,j);
    }

    @Override
    public Integer sumByLikeNumMore(int i) {
        return commentDao.sumByLikeNumMore(i);
    }
}
