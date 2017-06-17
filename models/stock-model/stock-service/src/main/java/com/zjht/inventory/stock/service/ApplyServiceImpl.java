package com.zjht.inventory.stock.service;

import com.zjht.commons.utils.StatusUtil;
import com.zjht.inventory.stock.dao.ApplyWriteDao;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.ApplyListProperty;
import com.zjht.inventory.stock.entity.Lock;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 *
 */
@ExportService(serviceTypes = {ApplyService.class})
public class ApplyServiceImpl implements ApplyService {
    @ImportService
    private Generator generator;
    @ImportService
    private ApplyWriteDao applyWriteDao;
    @ImportService
    private LockService lockService;

    @Override
    @Transactional
    public Apply createApply(Apply apply) {
        Long applyId = next(Apply.SEQ);
        //设置申请单主键
        apply.setId(applyId);
        apply.setSerialNumber(applyId.toString());
        apply.setHandleResult(StatusUtil.HANDLE_RESULT_AWAIT);
        //永久化申请单
        applyWriteDao.saveApply(apply);

        ArrayList<ApplyList> applyLists = apply.getApplyLists();
        for (ApplyList al : applyLists) {
            Long listId = next(ApplyList.SEQ);
            //设置清单主键
            al.setId(listId);
            //设置是申请单关联
            al.setApplyId(applyId);
            //永久化清单
            applyWriteDao.saveApplyList(al);

            ArrayList<ApplyListProperty> applyListProperties = al.getApplyListProperties();

            for (ApplyListProperty alp : applyListProperties){
                Long propertyId = next(ApplyListProperty.SEQ);
                //设置属性表主键
                alp.setId(propertyId);
                //设置清单关联
                alp.setListId(listId);
                //永久化属性列表
                applyWriteDao.saveApplyListProperty(alp);
            }
        }

        //锁定信息持久化
        if(apply.getApplyType()== StatusUtil.STOCK_LOCK){
            for(ApplyList applyList : applyLists) {
                long id = next(Lock.SEQ);
                Lock lock = new Lock(id, apply.getId(), apply.getWarehouseBelong(), applyList);
                lockService.saveLock(lock);
            }
        }

        return apply;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private Long next(String seq) {
        return generator.next(seq);
    }

    @Override
    public void unlock(Long applyId, String userName, String handleRemark) {
        //更改处理结果
        applyWriteDao.changeHandleResultById(applyId, userName, StatusUtil.HANDLE_RESULT_UNLOCK, handleRemark);
        //删除锁定信息
        lockService.deleteByApplyId(applyId);
    }

    @Override
    @Transactional
    public void nullify(Long applyId, Long applyType, String username) {
        if(StatusUtil.STOCK_LOCK==applyType)
            lockService.deleteByApplyId(applyId);
        applyWriteDao.changeHandleResultById(applyId, username, StatusUtil.HANDLE_RESULT_NULLIFY, null);
    }

    @Override
    @Transactional
    public void convertApply(Apply apply, Long convertType, String remark) {
        applyWriteDao.convertType(apply.getId(), convertType, remark);
        lockService.deleteByApplyId(apply.getId());
    }

    @Override
    public void changeStatus(Long applyId) {
        applyWriteDao.changeStatus(applyId);
    }
}
