package com.bbt.back.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/12 0012 10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminDaoTest {
    @Autowired
    private AdminDao adminDao;


    @Test
    public  void findByUserId(){
        adminDao.findByAdminEmailAndPassword("1145054472@qq.com","123");
    }
}
