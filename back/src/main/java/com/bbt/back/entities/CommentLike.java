package com.bbt.back.entities;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 18:57
 */
public class CommentLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int commentLikeId;
    private int userId;
    private int commentId;
    private Date likeTime;

    public CommentLike() {
    }

    public CommentLike(int userId, int commentId, Date  likeTime) {
        this.userId = userId;
        this.commentId = commentId;
        this.likeTime = likeTime;
    }

    public CommentLike(int commentLikeId, int userId, int commentId, Date likeTime) {
        this.commentLikeId = commentLikeId;
        this.userId = userId;
        this.commentId = commentId;
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "commentLikeId=" + commentLikeId +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", likeTime=" + likeTime +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCommentLikeId() {
        return commentLikeId;
    }

    public void setCommentLikeId(int commentLikeId) {
        this.commentLikeId = commentLikeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date  getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date  likeTime) {
        this.likeTime = likeTime;
    }
}
