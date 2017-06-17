package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.commons.utils.DateUtils;
import com.zjht.inventory.stock.dao.mapper.ApplyMapper;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.ApplyListProperty;
import org.smarabbit.massy.annotation.ExportService;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 *
 */
@ExportService(serviceTypes = {ApplyDao.class, ApplyWriteDao.class})
public class ApplyDaoImpl implements ApplyWriteDao {
    public void setApplyMapper(ApplyMapper applyMapper) {
        this.applyMapper = applyMapper;
    }
    private ApplyMapper applyMapper;


    @Override
    public Apply findById(Long id) {
        return applyMapper.findById(id);
    }

    @Override
    public List<Apply> findAll(String orderId, String warehouseBelong, Long applyType,
                               Long status, Long handleResult,
                               String startTime, String endTime) {
        return applyMapper.findAll(orderId,warehouseBelong,applyType,status,
                handleResult,startTime,endTime);
    }

    @Override
    public Page<Apply> findByPage(String orderId, Long warehouseBelong, Long applyType, Long status, Long handleResult, String startTime, String endTime, int index, int size) {
        PageHelper.startPage(index,size);
        if(!StringUtils.isEmpty(endTime)){
            Date date = DateUtils.formatyyyyMMdd(endTime);
            Date time = DateUtils.getAfterDay(date);
            endTime = DateUtils.formatDateToStr(time);
        }
        return applyMapper.findByPage(orderId,warehouseBelong,applyType,status,
                handleResult,startTime,endTime);
    }

    @Override
    public Page<ApplyList> findApplyListByPage(String applyId, int index, int size) {
        PageHelper.startPage(index,size);
        return applyMapper.findApplyListByPage(applyId);
    }

    @Override
    public ApplyList findApplyListByListId(Long listId) {
        return applyMapper.findApplyListByListId(listId);
    }

    @Override
    public Apply findBySerialNumber(String serialNumber) {
        return applyMapper.findBySerialNumber(serialNumber);
    }

    @Override
    public void changeStatus(Long applyId) {
        applyMapper.changeStatus(applyId);
    }

    @Override
    public void saveApply(Apply apply) {
        applyMapper.saveApply(apply);
    }

    @Override
    public void saveApplyLists(List<ApplyList> applyLists) {
        applyMapper.saveApplyLists(applyLists);
    }

    @Override
    public void saveApplyListProperties(List<ApplyListProperty> applyListProperties) {
        applyMapper.saveApplyListProperties(applyListProperties);
    }

    @Override
    public void saveApplyList(ApplyList applyList) {
        applyMapper.saveApplyList(applyList);
    }

    @Override
    public void saveApplyListProperty(ApplyListProperty applyListProperty) {
        applyMapper.saveApplyListProperty(applyListProperty);
    }

    @Override
    public void changeHandleResultById(Long applyId, String handleBy, Long result, String handleRemark) {
        applyMapper.changeHandleResultById(applyId, handleBy, result, handleRemark);
    }

    @Override
    public void convertType(Long applyId, Long convertType, String remark) {
        applyMapper.convertType(applyId, convertType, remark);
    }
}
