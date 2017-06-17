package com.zjht.inventory.stock.dao;

/**
 * Created by Jason on 2016/10/26.
 */
public interface InventoryDetailedWriteDao extends InventoryDetailedDao {
    /**
     * 根据明细主键更改明细状态
     * @param id
     * @param status
     */
    void changeStatusById(Long id, long status);
}
