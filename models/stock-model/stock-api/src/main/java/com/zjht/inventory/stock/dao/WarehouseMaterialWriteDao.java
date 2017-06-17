package com.zjht.inventory.stock.dao;

public interface  WarehouseMaterialWriteDao extends WarehouseMaterialDao {

    void updateCount(Long warehouseMaterialId, Long countNum);

    void insertOne(Long id, Long warehouseId, Long materielId, String remark);
}
