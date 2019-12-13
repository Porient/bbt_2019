package com.bbt.back.service.impl;

import com.bbt.back.dao.CollectDao;
import com.bbt.back.dao.ComputerDao;
import com.bbt.back.dao.PhoneDao;
import com.bbt.back.entities.Collect;
import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;
import com.bbt.back.entities.Record;
import com.bbt.back.service.CollectService;
import com.bbt.back.utils.MapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/11 18:06
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private PhoneDao phoneDao;

    @Override
    public PageInfo<Collect> selectAllByUserId(int userId, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == -1 ? 1 : pageNum;
        pageSize = pageSize == -1 ? 10 : pageSize;
        List<Collect> list=collectDao.selectByUserId(userId);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Collect> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int addCollect(Collect collect) {
        return collectDao.insertCollect(collect);
    }

    @Override
    public int deleteCollect(int collectId) {
        return collectDao.deleteCollect(collectId);
    }

    @Override
    public Long findCollectNumByUserId(Integer userId) {
        return collectDao.countByUserId(userId);
    }

    @Override
    public Integer rankByUserId(Integer userId) {
        Map<Integer, Long> map = new HashMap<>();
        Integer rank=-1;
        List<HashMap<Long, Object>> list = collectDao.sumByUserIdList();
        if (list != null && !list.isEmpty()) {
            for (HashMap<Long, Object> map1 : list) {
                Integer key = null;
                Long value = null;
                for (Map.Entry<Long, Object> entry : map1.entrySet()) {
                    if ("key".equals(entry.getKey())) {
                        key = (Integer) entry.getValue();
                    } else if ("value".equals(entry.getKey())) {
                        value = (Long) entry.getValue();
                    }
                }
                map.put(key, value);
            }
        }

        List<Map.Entry<Integer, Long>> sortList = new ArrayList<>();
        for(Map.Entry<Integer, Long> entry : map.entrySet()){
            sortList.add(entry); //将map中的元素放入list中
        }

        Collections.sort(sortList, new Comparator<Map.Entry<Integer, Long>>() {
            @Override
            public int compare(Map.Entry<Integer, Long> o1, Map.Entry<Integer, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < sortList.size(); i++) {
            if(sortList.get(i).getKey()==userId){
                rank=i+1;
                break;
            }
        }
        return rank;
    }

    @Override
    public HashMap<String, Integer> sumBrandByUserId(Integer userId) {
        HashMap<String, Integer> result = new HashMap<>();
        List<Collect> collectList=collectDao.selectByUserId(userId);
        for (Collect c:collectList){
            if (c.getProductType()==0){
                Phone phone=phoneDao.findPhoneById(c.getProductId());
                String brand=phone.getBrand();
                result=MapUtil.addKey(result,brand);
            }else {
                Computer computer=computerDao.findComputerById(c.getProductId());
                String brand=computer.getBrand();
                result=MapUtil.addKey(result,brand);
            }
        }
        return result;
    }

    @Override
    public HashMap<String, Integer> sumTagByUserId(Integer userId) {
        HashMap<String, Integer> result = new HashMap<>();
        List<Collect> collectList=collectDao.selectByUserId(userId);
        for (Collect c:collectList){
            if (c.getProductType()==0){
                Phone phone=phoneDao.findPhoneById(c.getProductId());
                String tag1=phone.getTag1();
                String tag2=phone.getTag2();
                String tag3=phone.getTag3();
                result=MapUtil.addKey(result,tag1);
                result=MapUtil.addKey(result,tag2);
                result=MapUtil.addKey(result,tag3);
            }else {
                Computer computer=computerDao.findComputerById(c.getProductId());
                String tag1=computer.getTag1();
                String tag2=computer.getTag2();
                String tag3=computer.getTag3();
                result=MapUtil.addKey(result,tag1);
                result=MapUtil.addKey(result,tag2);
                result=MapUtil.addKey(result,tag3);
            }
        }
        return result;
    }
}
