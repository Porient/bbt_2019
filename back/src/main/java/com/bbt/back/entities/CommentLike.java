package com.bbt.back.entities;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 18:57
 */
public class CommentLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int CommentLikeId;
    private int userId;
    private int commentId;

    public CommentLike() {
    }

    public CommentLike(int commentLikeId, int userId, int commentId) {
        CommentLikeId = commentLikeId;
        this.userId = userId;
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "CommentLikeId=" + CommentLikeId +
                ", userId=" + userId +
                ", commentId=" + commentId +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCommentLikeId() {
        return CommentLikeId;
    }

    public void setCommentLikeId(int commentLikeId) {
        CommentLikeId = commentLikeId;
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
}
