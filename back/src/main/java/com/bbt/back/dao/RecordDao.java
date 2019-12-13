package com.bbt.back.dao;

import com.bbt.back.entities.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:51
 */
@Component
public interface RecordDao {
    int deleteRecord(Integer id);

    int insertRecord(Record record);

    Record findRecordById(Integer id);

    int updateRecord(Record record);

    List<Record> selectAll();

    //新增
    List<Record> selectByUserId(int userId);

    Long findRecordNumByUserId(Integer userId);

    Integer findProductNumByUserIdAndType(@Param("userId")Integer userId ,@Param("productType") Integer productType);

    List<HashMap<Integer,Object>> sumByUserIdList();

    List<HashMap<String,Object>> sumByTimeList();
}
