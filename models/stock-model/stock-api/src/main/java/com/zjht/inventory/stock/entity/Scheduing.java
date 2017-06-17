package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/18.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * 出/入库记录
 */
public class Scheduing implements Serializable {

    public static final String SEQ = "T_IN_OUT_RECORD_SEQ";

    /**
     * 全参构造方法
     *
     * @param id
     * @param systrace
     * @param inOutType
     * @param applyId
     * @param orderId
     * @param operator
     * @param operateTime
     * @param drawingPerson
     */
    public Scheduing(
        Long id, String systrace, Long inOutType, Long applyId, String orderId, String operator,
        Date operateTime, String drawingPerson, Long warehouseId) {
        this.id = id;
        this.systrace = systrace;
        this.inOutType = inOutType;
        this.applyId = applyId;
        this.orderId = orderId;
        this.operator = operator;
        this.operateTime = operateTime;
        this.drawingPerson = drawingPerson;
        this.warehouseId = warehouseId;
    }

    /**
     * 出/入库编号
     */
    private Long   id;
    /**
     * 系统流水号
     */
    private String systrace;
    /**
     * 出/入库类型
     * 1生产出库
     * 2调拨出库
     * 3返修出库
     * 4换机出库
     * 5借用出库
     * <p>
     * 8锁定
     * <p>
     * 14盘点入库
     * 15采购入库
     * 16生产入库
     * 17调拨入库
     * 18返修入库
     * 19换机入库
     * 20借用入库
     */
    private Long   inOutType;
    private String inOutTypeName;
    /**
     * 申请编号
     */
    private Long   applyId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 操作人编号
     */
    private String operator;
    /**
     * 操作时间
     */
    private Date   operateTime;
    /**
     * 领机人
     */
    private String drawingPerson;
    /**
     * 仓库编号
     */
    private Long   warehouseId;
    private String warehouseName;

    public Scheduing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystrace() {
        return systrace;
    }

    public void setSystrace(String systrace) {
        this.systrace = systrace;
    }

    public Long getInOutType() {
        return inOutType;
    }

    public void setInOutType(Long inOutType) {
        this.inOutType = inOutType;
    }

    public String getInOutTypeName() {
        return inOutTypeName;
    }

    public void setInOutTypeName(String inOutTypeName) {
        this.inOutTypeName = inOutTypeName;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getDrawingPerson() {
        return drawingPerson;
    }

    public void setDrawingPerson(String drawingPerson) {
        this.drawingPerson = drawingPerson;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public String toString() {
        return "Scheduing{" + "id=" + id + ", systrace='" + systrace + '\'' + ", inOutType=" +
               inOutType + ", inOutTypeName='" + inOutTypeName + '\'' + ", applyId=" + applyId +
               ", orderId='" + orderId + '\'' + ", operator='" + operator + '\'' +
               ", operateTime=" + operateTime + ", drawingPerson='" + drawingPerson + '\'' +
               ", warehouseId='" + warehouseId + '\'' + ", warehouseName='" + warehouseName + '\'' +
               '}';
    }
}
