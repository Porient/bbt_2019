package com.bbt.back.model;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 16:15
 */
public class DeleteObject {
    private List<ProductLikeObject> likeObjects;

    public DeleteObject(List<ProductLikeObject> likeObjects) {
        this.likeObjects = likeObjects;
    }

    public List<ProductLikeObject> getLikeObjects() {
        return likeObjects;
    }

    public void setLikeObjects(List<ProductLikeObject> likeObjects) {
        this.likeObjects = likeObjects;
    }
}
