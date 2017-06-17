package com.zjht.inventory.stock.dao.mapper;

import com.zjht.inventory.stock.entity.Lock;
import com.zjht.inventory.stock.entity.LockProperty;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 *
 */
public interface LockMapper {

    void saveLock(@Param("lock") Lock lock);

    void saveLockProperty(@Param("lockProperty") LockProperty lockProperty);

    ArrayList<Lock> findByApplyId(@Param("applyId") Long applyId);

    void deleteLockPropertyByLockId(@Param("lockId") Long lockId);

    void deleteById(@Param("id") Long id);

    ArrayList<Lock> findByWarehouseMaterial(@Param("warehouseId") Long warehouseId,
                                            @Param("materielId") Long materielId);
}
