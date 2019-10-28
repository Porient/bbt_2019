package com.bbt.back.service.impl;

import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.dao.RecordDao;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.Record;
import com.bbt.back.service.RecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/15 11:21
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private PhoneDao phoneDao;

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

    @Override
    public Integer findRecordNumByUserId(Integer userId) {
        return recordDao.findRecordNumByUserId(userId);
    }

    @Override
    public Integer findComputerNumByUserId(Integer userId) {
        return recordDao.findProductNumByUserIdAndType(userId,1);
    }

    @Override
    public Integer findPhoneNumByUserId(Integer userId) {
        return recordDao.findProductNumByUserIdAndType(userId,0);
    }

    @Override
    public HashMap<String, Integer> sumByUserId(Integer userId) {
        Map<Integer, Integer> map = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        List<HashMap<Integer, Object>> list = recordDao.sumByUserIdList();
        if (list != null && !list.isEmpty()) {
            for (HashMap<Integer, Object> map1 : list) {
                Integer key = null;
                Integer value = null;
                for (Map.Entry<Integer, Object> entry : map1.entrySet()) {
                    if ("key".equals(entry.getKey())) {
                        key = (Integer) entry.getValue();
                    } else if ("value".equals(entry.getKey())) {
                        value = (Integer) entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }

        List<Map.Entry<Integer, Integer>> sortList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i=0;i<5;i++){
            Integer recordId =sortList.get(i).getKey();
            Record record=recordDao.findRecordById(recordId);
            String productName="";
            if(record.getProductType()==0){
                Phone phone = phoneDao.findPhoneById(record.getProductId());
                productName=phone.getProductName();
            }else {
                Computer computer = computerDao.findComputerById(record.getProductId());
                productName=computer.getBrand();
            }
            result.put(productName,sortList.get(i).getValue());
        }

        return result;
    }

    @Override
    public HashMap<String, Integer> sumByTime() {
        Map<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        List<HashMap<String, Object>> list = recordDao.sumByTimeList();
        if (list != null && !list.isEmpty()) {
            for (HashMap<String, Object> map1 : list) {
                String key = null;
                Integer value = null;
                for (Map.Entry<String, Object> entry : map1.entrySet()) {
                    if ("key".equals(entry.getKey())) {
                        key = (String) entry.getValue();
                    } else if ("value".equals(entry.getKey())) {
                        value = (Integer) entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (int i=0;i<30;i++){
            result.put(sortList.get(i).getKey(),sortList.get(i).getValue());
        }

        return result;
    }
}
