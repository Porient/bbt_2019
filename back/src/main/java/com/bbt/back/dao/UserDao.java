package com.bbt.back.dao;

import com.bbt.back.entities.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 用户Dao
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:58
 */
@Component
public interface UserDao {
    int deleteUser(Integer id);

    int insertUser(User user);

    User findUserById(Integer id);

    List<User> selectAll();

    int updateUser(User user);

    User findByUserEmail(String userEmail);

    User findByUserEmailAndPassword(@Param("userEmail")String userEmail, @Param("password") String password);

    List<User> getUserListByParams();
}
