package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/18.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 出/入库申请表.
 */
public class Apply implements Serializable {

    private static final long version = 1L;
    public static final String SEQ = "T_APPLY_SEQ";

    /**
     * 全参构造
     * @param id
     * @param serialNumber
     * @param orderId
     * @param createTime
     * @param createBy
     * @param warehouseBelong
     * @param warehouseName
     * @param applyType
     * @param applyTypeName
     * @param remark
     * @param status
     * @param handleBy
     * @param handleResult
     * @param handleResultName
     * @param handleRemark
     */
    public Apply(Long id, String serialNumber, String orderId, Date createTime,
                 String createBy, Long warehouseBelong, String warehouseName, Long applyType,
                 String applyTypeName, String remark, Long status, String handleBy,
                 Long handleResult, String handleResultName, String handleRemark) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.orderId = orderId;
        this.createTime = createTime;
        this.createBy = createBy;
        this.warehouseBelong = warehouseBelong;
        this.warehouseName = warehouseName;
        this.applyType = applyType;
        this.applyTypeName = applyTypeName;
        this.remark = remark;
        this.status = status;
        this.handleBy = handleBy;
        this.handleResult = handleResult;
        this.handleResultName = handleResultName;
        this.handleRemark = handleRemark;
    }

    /**
     * 申请编号
     */
    private Long id ;
    /**
     * 外部接口所用唯一标识
     */
    private String serialNumber ;
    /**
     * 订单编号
     */
    private String orderId ;
    /**
     * 申请时间
     */
    private Date createTime ;
    /**
     * 创建人主键
     */
    private String createBy ;
    /**
     * 仓库主键
     */
    private Long warehouseBelong ;
    private String warehouseName ;
    /**
     * 申请类型:
         1生产出库
         2调拨出库
         3返修出库
         4换机出库
         5借用出库

         8锁定

         14盘点入库
         15采购入库
         16生产入库
         17调拨入库
         18返修入库
         19换机入库
         20借用入库
     */
    private Long applyType ;
    private String applyTypeName ;
    /**
     * 申请说明
     */
    private String remark ;
    /**
     * 处理状态:
         1待处理,
         2已处理,
         说明:申请类型为锁定时,待处理下显示锁定操作,已处理下显示解锁操作
     */
    private Long status ;
    /**
     * 处理人主键
     */
    private String handleBy ;
    /**
     * 处理结果:
         1.未处理
         2.已入库
         3.已出库
         4.已锁定
         5.已解锁
     */
    private Long handleResult ;
    private String handleResultName ;
    /**
     * 处理说明
     */
    private String handleRemark ;
    /**
     * 清单列表
     */
    private ArrayList<ApplyList> applyLists ;

    public ArrayList<ApplyList> getApplyLists() {
        return applyLists;
    }

    public void setApplyLists(ArrayList<ApplyList> applyLists) {
        this.applyLists = applyLists;
    }

    public Apply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getWarehouseBelong() {
        return warehouseBelong;
    }

    public void setWarehouseBelong(Long warehouseBelong) {
        this.warehouseBelong = warehouseBelong;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getApplyType() {
        return applyType;
    }

    public void setApplyType(Long applyType) {
        this.applyType = applyType;
    }

    public String getApplyTypeName() {
        return applyTypeName;
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getHandleBy() {
        return handleBy;
    }

    public void setHandleBy(String handleBy) {
        this.handleBy = handleBy;
    }

    public Long getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Long handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleResultName() {
        return handleResultName;
    }

    public void setHandleResultName(String handleResultName) {
        this.handleResultName = handleResultName;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", warehouseBelong=" + warehouseBelong +
                ", warehouseName='" + warehouseName + '\'' +
                ", applyType=" + applyType +
                ", applyTypeName='" + applyTypeName + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", handleBy='" + handleBy + '\'' +
                ", handleResult=" + handleResult +
                ", handleResultName='" + handleResultName + '\'' +
                ", handleRemark='" + handleRemark + '\'' +
                ", applyLists=" + applyLists +
                '}';
    }

}
