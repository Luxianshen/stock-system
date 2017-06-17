package com.zjht.inventory.extensions.webinterface.ui.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by Jason on 2016/10/20.
 */
public class ApplyEntity {

    /**
     * 申请编号
     */
    @NotEmpty(message = "申请编号不能为空")
    private String id ;
    /**
     * 订单编号
     */
    private String orderId ;
    /**
     * 创建人主键
     */
    private String createBy ;
    /**
     * 仓库主键
     */
    @NotEmpty(message = "仓库编号不能为空")
    private String warehouseBelong ;
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
    @NotEmpty(message = "申请类型不能为空")
    @Pattern(regexp = 1+"-"+20, message = "请提交有效的申请类型")
    private Number applyType ;
    /**
     * 申请说明
     */
    private String remark ;

    public ApplyEntity() {
    }


    public ApplyEntity(String id, String orderId, String createBy, String warehouseBelong, Number applyType, String remark) {
        this.id = id;
        this.orderId = orderId;
        this.createBy = createBy;
        this.warehouseBelong = warehouseBelong;
        this.applyType = applyType;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getWarehouseBelong() {
        return warehouseBelong;
    }

    public void setWarehouseBelong(String warehouseBelong) {
        this.warehouseBelong = warehouseBelong;
    }

    public Number getApplyType() {
        return applyType;
    }

    public void setApplyType(Number applyType) {
        this.applyType = applyType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ApplyEntity{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", warehouseBelong='" + warehouseBelong + '\'' +
                ", applyType=" + applyType +
                ", remark='" + remark + '\'' +
                '}';
    }
}
