package com.bbt.back.dao;

import com.bbt.back.entities.Record;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:51
 */
public interface RecordDao {
    int deleteRecord(Integer id);

    int insertRecord(Record record);

    Record findRecordById(Integer id);

    int updateRecord(Record record);

    List<Record> selectAll();
}
