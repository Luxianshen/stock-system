package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Lock;

import java.util.ArrayList;

/**
 * Created by Jason on 2016/10/18.
 */
public interface LockDao {


    ArrayList<Lock> findByApplyId(Long applyId);

    ArrayList<Lock> findByWarehouseMaterial(Long warehouseId, Long materielId);
}
