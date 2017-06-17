package com.zjht.inventory.stock.entity;

import java.io.Serializable;

/**
 * Created by Jason on 2016/11/16.
 */
public class InOutDetail implements Serializable {
    public static final String SEQ = "T_IN_OUT_DETAILED_SEQ";

    /**
     *
     * @param id
     * @param systemNo
     * @param materielId
     * @param inventoryDetailedId
     * @param operateNum
     */
    public InOutDetail(Long id, String systemNo, Long materielId, Long inventoryDetailedId, Long operateNum) {
        this.id = id;
        this.systemNo = systemNo;
        this.materielId = materielId;
        this.inventoryDetailedId = inventoryDetailedId;
        this.operateNum = operateNum;
    }

    private Long id;
    private String systemNo;
    private Long materielId;
    private Long inventoryDetailedId;
    private Long operateNum;

    public InOutDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public Long getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Long materielId) {
        this.materielId = materielId;
    }

    public Long getInventoryDetailedId() {
        return inventoryDetailedId;
    }

    public void setInventoryDetailedId(Long inventoryDetailedId) {
        this.inventoryDetailedId = inventoryDetailedId;
    }

    public Long getOperateNum() {
        return operateNum;
    }

    public void setOperateNum(Long operateNum) {
        this.operateNum = operateNum;
    }

    @Override
    public String toString() {
        return "InOutDetail{" +
                "id=" + id +
                ", systemNo='" + systemNo + '\'' +
                ", materielId=" + materielId +
                ", inventoryDetailedId=" + inventoryDetailedId +
                ", operateNum=" + operateNum +
                '}';
    }
}
