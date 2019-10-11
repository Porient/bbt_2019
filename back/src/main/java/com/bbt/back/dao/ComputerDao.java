package com.bbt.back.dao;

import com.bbt.back.entities.Computer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/10/6 15:57
 */
@Component
public interface ComputerDao {
    int deleteComputer(Integer id);

    int insertComputer(Computer computer);

    Computer findComputerById(Integer id);

    int updateComputer(Computer computer);

    List<Computer> selectAll();
}