package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.ApplyListProperty;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface ApplyMapper {

    Apply findById(@Param("id") Long id);

    void saveApply(@Param("apply") Apply apply);

    void saveApplyLists(@Param("applyLists") List<ApplyList> applyLists);

    void saveApplyListProperties(@Param("applyListProperties") List<ApplyListProperty> applyListProperties);


    ArrayList<Apply> findAll(@Param("orderId") String orderId,
                             @Param("warehouseBelong") String warehouseBelong,
                             @Param("applyType") Long applyType,
                             @Param("status") Long status,
                             @Param("handleResult") Long handleResult,
                             @Param("startTime") String startTime,
                             @Param("endTime") String endTime);

    Page<Apply> findByPage(@Param("orderId") String orderId,
                           @Param("warehouseBelong") Long warehouseBelong,
                           @Param("applyType") Long applyType,
                           @Param("status") Long status,
                           @Param("handleResult") Long handleResult,
                           @Param("startTime") String startTime,
                           @Param("endTime") String endTime);


    Page<ApplyList> findApplyListByPage(@Param("applyId") String applyId);

    ApplyList findApplyListByListId(@Param("listId") Long listId);

    void saveApplyList(@Param("applyList") ApplyList applyList);

    void saveApplyListProperty(@Param("applyListProperty") ApplyListProperty applyListProperty);

    void changeHandleResultById(@Param("applyId") Long applyId,
                                @Param("handleBy") String handleBy,
                                @Param("result") Long result,
                                @Param("handleRemark") String handleRemark);

    void convertType(@Param("applyId") Long applyId,
                     @Param("convertType") Long convertType,
                     @Param("remark") String remark);

    Apply findBySerialNumber(@Param("serialNumber") String serialNumber);

    void changeStatus(@Param("id") Long applyId);
}
