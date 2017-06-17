package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Manager;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/20.
 */
public interface ManagerDao {
    List<Manager> findAllManager();
}
