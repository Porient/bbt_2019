package com.bbt.back.model;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 16:15
 */
public class DeleteObject {
    private List<ProductLikeObject> likeObject;

    public DeleteObject(List<ProductLikeObject> likeObject) {
        this.likeObject = likeObject;
    }

    public List<ProductLikeObject> getLikeObjects() {
        return likeObject;
    }

    public void setLikeObjects(List<ProductLikeObject> likeObject) {
        this.likeObject = likeObject;
    }
}
