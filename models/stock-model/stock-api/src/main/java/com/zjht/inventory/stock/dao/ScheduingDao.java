package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Scheduing;
import com.zjht.inventory.stock.entity.ScheduingDetail;

/**
 * Created by Jason on 2016/10/18.
 */
public interface ScheduingDao {

    Scheduing findById(String id);

    Page<Scheduing> findByPage(Long applyId, String orderId, Long inOutType, String operator, String drawingPerson, String startTime, String endTime, int index, int size);

    ScheduingDetail findDetailBySystrace(String systrace);

    Scheduing findBySystrace(String systrace);
}
