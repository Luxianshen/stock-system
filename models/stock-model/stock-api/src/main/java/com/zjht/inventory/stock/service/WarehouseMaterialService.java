package com.zjht.inventory.stock.service;

/**
 * Created by longshuzhen on 2017/1/12。
 */
public interface WarehouseMaterialService {
    void insertOne(Long id, Long warehouseId, Long materielId, String remark);
}
