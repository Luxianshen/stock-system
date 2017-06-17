package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.ApplyListProperty;

import java.util.List;

/**
 * Created by Jason on 2016/10/18.
 */
public interface ApplyWriteDao extends ApplyDao {

    void saveApply(Apply apply);

    void saveApplyLists(List<ApplyList> applyLists);

    void saveApplyListProperties(List<ApplyListProperty> applyListProperties);

    void saveApplyList(ApplyList applyList);

    void saveApplyListProperty(ApplyListProperty applyListProperty);

    void changeHandleResultById(Long applyId, String handleBy, Long result, String handleRemark);

    void convertType(Long applyId, Long convertType, String remark);
}
