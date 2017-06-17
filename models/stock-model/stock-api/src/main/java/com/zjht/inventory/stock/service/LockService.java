package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.Lock;

/**
 * Created by Jason on 2016/10/18.
 */
public interface LockService {
    void saveLock(Lock lock);

    void deleteByApplyId(Long applyId);
}
