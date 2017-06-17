package com.zjht.inventory.stock.dao;


import com.zjht.inventory.stock.entity.WarehouseInfo;

/**
 * Created by longshuzhen on 2016/10/18.
 */
public interface WarehouseInfoWriteDao extends WarehouseInfoDao{

    int updateWarehouse(WarehouseInfo warehouse);
    int createWarehouse(WarehouseInfo warehouse);
    int removeWarehouse(WarehouseInfo warehouse);

}
