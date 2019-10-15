package com.bbt.back.service.impl;

import com.bbt.back.dao.RecordDao;
import com.bbt.back.entities.Record;
import com.bbt.back.service.RecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 11:21
 */
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;
    @Override
    public PageInfo<Record> selectAllByUserId(int userId, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == -1 ? 1 : pageNo;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Record> records = recordDao.selectByUserId(userId);
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(records);
    }

    @Override
    public int deleteById(int recordId) {
        return recordDao.deleteRecord(recordId);
    }

    @Override
    public int addRecord(Record record) {
        return recordDao.insertRecord(record);
    }

    @Override
    public Record findById(int recordId) {
        return recordDao.findRecordById(recordId);
    }
}
