package com.bbt.back.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 16:15
 */
public class DeleteObject implements Serializable {
    private List<ProductLikeObject> likeObject;

    public DeleteObject(){

    }

    public DeleteObject(List<ProductLikeObject> likeObject) {
        this.likeObject = likeObject;
    }

    public List<ProductLikeObject> getLikeObject() {
        return likeObject;
    }

    public void setLikeObject(List<ProductLikeObject> likeObject) {
        this.likeObject = likeObject;
    }

    @Override
    public String toString() {
        return "DeleteObject{" +
                "likeObject=" + likeObject +
                '}';
    }
}
