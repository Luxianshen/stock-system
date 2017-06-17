package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.WarehouseInfo;

/**
 * Created by longshuzhen on 2016/10/18.
 */
public interface WarehouseInfoService {

    void updateWarehouse(WarehouseInfo warehouse);
    void createWarehouse(WarehouseInfo warehouse);
    void removeWarehouse(WarehouseInfo warehouse);
}
