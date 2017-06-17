package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.InventoryDetailed;

/**
 * Created by Jason on 2016/10/26.
 */
public interface InventoryDetailedDao {
    InventoryDetailed findById(Long id, String table);
}
