package com.bbt.back.model;

import com.bbt.back.entities.Comment;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/11 16:46
 */
public class CommentAbbr {
    private int commentCount;
    private int likeCount;
    private Comment comment;

    public CommentAbbr() {
    }

    public CommentAbbr(int commentCount, int likeCount, Comment comment) {
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.comment = comment;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
