package com.bbt.back.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/28 0028 15:34
 */
public class CommentPic implements Serializable {
    Integer commentNum;
    Integer rank;
    Integer likeNum1;
    Integer likeNum2;
    Integer likeNum3;
    Integer likeNum4;

    public CommentPic(){

    }

    public CommentPic(Integer commentNum, Integer rank, Integer likeNum1, Integer likeNum2, Integer likeNum3, Integer likeNum4) {
        this.commentNum = commentNum;
        this.rank = rank;
        this.likeNum1 = likeNum1;
        this.likeNum2 = likeNum2;
        this.likeNum3 = likeNum3;
        this.likeNum4 = likeNum4;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLikeNum1() {
        return likeNum1;
    }

    public void setLikeNum1(Integer likeNum1) {
        this.likeNum1 = likeNum1;
    }

    public Integer getLikeNum2() {
        return likeNum2;
    }

    public void setLikeNum2(Integer likeNum2) {
        this.likeNum2 = likeNum2;
    }

    public Integer getLikeNum3() {
        return likeNum3;
    }

    public void setLikeNum3(Integer likeNum3) {
        this.likeNum3 = likeNum3;
    }

    public Integer getLikeNum4() {
        return likeNum4;
    }

    public void setLikeNum4(Integer likeNum4) {
        this.likeNum4 = likeNum4;
    }
}
