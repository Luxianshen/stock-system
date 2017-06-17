package com.zjht.inventory.stock.entity;

import java.io.Serializable;

/**
 * 仓库物料表
 */

public class WarehouseMaterial implements Serializable {

    public static final String SEQ = "T_WAREHOUSE_MATERIAL_SEQ";
    /**
     * 主键
     */
    private Long   id;
    /**
     * 仓库主键
     */
    private Long   warehouseId;
    /**
     * 物料主键
     */
    private Long   materialId;
    /**
     * 库存数量
     */
    private Number inventoryCount;
    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Number getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Number inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    @Override
    public String toString() {
        return "WarehouseMaterial [id=" + id + ", warehouseId=" + warehouseId + ", materialId=" +
               materialId + ", inventoryCount=" + inventoryCount + ", remark=" + remark + "]";
    }

}
