package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.dao.LockWriteDao;
import com.zjht.inventory.stock.entity.Lock;
import com.zjht.inventory.stock.entity.LockProperty;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@ExportService(serviceTypes = {LockService.class})
public class LockServiceImpl implements LockService {
    @ImportService
    private LockWriteDao lockWriteDao;

    @Override
    @Transactional
    public void saveLock(Lock lock) {
        lockWriteDao.saveLock(lock);
        List<LockProperty> lockProperties = lock.getLockProperties();
        for (LockProperty lp : lockProperties){
            lockWriteDao.saveLockProperty(lp);
        }
    }

    @Override
    @Transactional
    public void deleteByApplyId(Long applyId) {
        List<Lock> locks = lockWriteDao.findByApplyId(applyId);
        for (Lock l : locks){
            lockWriteDao.deleteLockPropertyByLockId(l.getId());
            lockWriteDao.deleteById(l.getId());
        }
    }
}
