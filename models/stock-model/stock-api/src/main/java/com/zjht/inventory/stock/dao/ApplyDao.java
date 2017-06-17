package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;

import java.util.List;

/**
 * Created by Jason on 2016/10/18.
 */
public interface ApplyDao {

    Apply findById(Long id);

    List<Apply> findAll(String orderId, String warehouseBelong, Long applyType,
                        Long status, Long handleResult, String startTime, String endTime);

    Page<Apply> findByPage(String orderId, Long warehouseBelong, Long applyType,
                           Long status, Long handleResult, String startTime, String endTime,
                           int index, int size);


    Page<ApplyList> findApplyListByPage(String applyId, int index, int size);

    ApplyList findApplyListByListId(Long listId);

    /**
     * 根据接口查询需求,
     * 新增对外使用的唯一标识
     * @param serialNumber
     * @return
     */
    Apply findBySerialNumber(String serialNumber);

    void changeStatus(Long applyId);
}
