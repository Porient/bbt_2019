package com.bbt.back.dao;

import com.bbt.back.entities.Attitude;
import com.bbt.back.entities.User;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:34
 */
public interface AttitudeDao {
    int deleteAttitude(Integer id);

    int insertAttitude(Attitude attitude);

    Attitude findAttitudeById(Integer id);

    int updateAttitude(Attitude attitude);

    List<Attitude> selectAll();
}
