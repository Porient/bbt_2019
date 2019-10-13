package com.bbt.back.model;

import com.bbt.back.entities.Computer;
import com.bbt.back.entities.Phone;

import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/13 19:53
 */
public class ProductList {
    private List<Phone> phones;
    private List<Computer> computers;

    public ProductList(List<Phone> phones, List<Computer> computers) {
        this.phones = phones;
        this.computers = computers;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }
}
