package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Lock;
import com.zjht.inventory.stock.entity.LockProperty;

/**
 * Created by Jason on 2016/10/18.
 */
public interface LockWriteDao extends LockDao {


    void saveLock(Lock lock);

    void saveLockProperty(LockProperty lockProperty);

    void deleteLockPropertyByLockId(Long lockId);

    void deleteById(Long id);
}
