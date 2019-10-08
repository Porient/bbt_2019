package com.bbt.back.dao;

import com.bbt.back.entities.Crawler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 0006 16:52
 */
@Component
public interface CrawlerDao {
    int deleteCrawler(Integer id);

    int insertCrawler(Crawler crawler);

    Crawler findCrawlerById(Integer id);

    int updateCrawler(Crawler crawler);

    List<Crawler> selectAll();
}
