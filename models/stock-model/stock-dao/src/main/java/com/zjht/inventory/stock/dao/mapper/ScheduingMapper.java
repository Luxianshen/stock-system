package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Scheduing;
import com.zjht.inventory.stock.entity.ScheduingDetail;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jason on 2016/10/31.
 */
public interface ScheduingMapper {

    Scheduing findById(@Param("id") String id);

    Page<Scheduing> findByPage(@Param("applyId") Long applyId,
                               @Param("orderId") String orderId,
                               @Param("inOutType") Long inOutType,
                               @Param("operator") String operator,
                               @Param("drawingPerson") String drawingPerson,
                               @Param("startTime") String startTime,
                               @Param("endTime") String endTime);

    ScheduingDetail findDetailBySystrace(@Param("systrace") String systrace);

    Scheduing findBySystrace(@Param("systrace") String systrace);
}
