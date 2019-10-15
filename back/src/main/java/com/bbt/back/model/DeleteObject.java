package com.bbt.back.model;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 16:15
 */
public class DeleteObject {
    private List<LikeObject> likeObjects;

    public DeleteObject(List<LikeObject> likeObjects) {
        this.likeObjects = likeObjects;
    }

    public List<LikeObject> getLikeObjects() {
        return likeObjects;
    }

    public void setLikeObjects(List<LikeObject> likeObjects) {
        this.likeObjects = likeObjects;
    }
}
