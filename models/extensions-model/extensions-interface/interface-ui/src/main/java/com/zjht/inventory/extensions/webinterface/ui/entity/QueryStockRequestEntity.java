package com.zjht.inventory.extensions.webinterface.ui.entity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2016/10/20.
 */
public class QueryStockRequestEntity {
    /**
     * 仓库编号
     */
    @NotNull( message = "仓库编号不能为空")
    private String warehouseId ;
    /**
     * 物料编号
     */
    @NotNull( message = "物料编号不能为空")
    private String materialId ;
    /**
     * 属性集合
     */
    private HashMap<String,String> properties;

    public QueryStockRequestEntity() {
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "QueryStockRequestEntity{" +
                "warehouseId='" + warehouseId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", properties=" + properties +
                '}';
    }
}
