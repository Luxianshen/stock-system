package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.LockMapper;
import com.zjht.inventory.stock.entity.Lock;
import com.zjht.inventory.stock.entity.LockProperty;
import org.smarabbit.massy.annotation.ExportService;

import java.util.ArrayList;

/**
 *
 */
@ExportService(serviceTypes = {LockDao.class, LockWriteDao.class})
public class LockDaoImpl implements LockWriteDao {
    public void setLockMapper(LockMapper lockMapper) {
        this.lockMapper = lockMapper;
    }
    private LockMapper lockMapper;

    @Override
    public void saveLock(Lock lock) {
        lockMapper.saveLock(lock);
    }

    @Override
    public void saveLockProperty(LockProperty lockProperty) {
        lockMapper.saveLockProperty(lockProperty);
    }

    @Override
    public void deleteLockPropertyByLockId(Long lockId) {
        lockMapper.deleteLockPropertyByLockId(lockId);
    }

    @Override
    public void deleteById(Long id) {
        lockMapper.deleteById(id);
    }

    @Override
    public ArrayList<Lock> findByApplyId(Long applyId) {
        return lockMapper.findByApplyId(applyId);
    }

    @Override
    public ArrayList<Lock> findByWarehouseMaterial(Long warehouseId, Long materielId) {
        return lockMapper.findByWarehouseMaterial(warehouseId,materielId);
    }
}
