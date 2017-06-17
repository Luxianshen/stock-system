package com.zjht.inventory.stock.dao.mapper;


import com.zjht.inventory.stock.entity.InventoryDetailed;

import org.apache.ibatis.annotations.Param;

import com.zjht.inventory.stock.entity.InventoryDetailed;

/**
 * Created by lujiasheng on 2016/10/18.
 */

public interface InventoryDetailedMapper {


    InventoryDetailed findById(@Param("id") Long id,
                               @Param("table") String table);

    void changeStatusById(@Param("id") Long id,
                          @Param("status") long status);
}
