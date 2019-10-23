package com.bbt.back.dao;

import com.bbt.back.entities.Phone;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:51
 */
@Component
public interface PhoneDao {
    int deletePhone(Integer id);

    int insertPhone(Phone phone);

    Phone findPhoneById(Integer id);

    int updatePhone(Phone phone);

    List<Phone> selectAll();

    //新增`
    int selectByProductId(int productId);

    List<Phone> selectAllPre();

    List<Phone> selectAllFormal();

    List<Phone> selectByType(int type);

    int changeState(int productIId, int i);

    List<Object> selectByToken(String searchToken);

    void likePhone(int productId);

    List<Object> selectByLibrary(int library);
}
