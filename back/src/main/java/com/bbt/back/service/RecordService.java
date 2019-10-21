package com.bbt.back.service;

import com.bbt.back.entities.Record;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

@Component
public interface RecordService {

    PageInfo<Record> selectAllByUserId(int userId, Integer pageNo, Integer pageSize);

    int deleteById(int recordId);

    int addRecord(Record record);

    Record findById(int recordId);
}