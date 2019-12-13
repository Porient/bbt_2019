package com.bbt.back.service.impl;

import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.dao.RecordDao;
import com.bbt.back.entities.Collect;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.Record;
import com.bbt.back.service.RecordService;
import com.bbt.back.utils.MapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Long findRecordNumByUserId(Integer userId) {
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
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        HashMap<String, Integer> recordMap = new HashMap<>();
        List<Record> recordList=recordDao.selectByUserId(userId);

        for (Record r:recordList){
            if (r.getProductType()==0){
                Phone phone=phoneDao.findPhoneById(r.getProductId());
                String brand=phone.getBrand();
                recordMap=MapUtil.addKey(recordMap,brand);
            }else {
                Computer computer=computerDao.findComputerById(r.getProductId());
                String brand=computer.getBrand();
                recordMap=MapUtil.addKey(recordMap,brand);
            }
        }

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : recordMap.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        if (sortList.size()<5){
            for (int i=0;i<sortList.size();i++){
                result.put(sortList.get(i).getKey(),sortList.get(i).getValue());
            }
        }else {
            for (int i=0;i<5;i++){
                result.put(sortList.get(i).getKey(),sortList.get(i).getValue());
            }
        }
        return result;
    }

    @Override
    public HashMap<String, Integer> sumByTime(Integer userId) {
        HashMap<String, Integer> map = new HashMap<>();
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        List<Record> recordList=recordDao.selectByUserId(userId);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for (Record record:recordList){
            Date BrowseTime=record.getBrowseTime();
            String date=ft.format(BrowseTime);
            map =  MapUtil.addKey(map,date);
        }

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                try {
                    return f.parse(o2.getKey()).compareTo(f.parse(o1.getKey()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        if (sortList.size()<30){
            for (int i=0;i<sortList.size();i++){
                result.put(sortList.get(i).getKey(),sortList.get(i).getValue());
            }
        }else {
            for (int i=0;i<30;i++){
                result.put(sortList.get(i).getKey(),sortList.get(i).getValue());
            }
        }

        return result;
    }
}
