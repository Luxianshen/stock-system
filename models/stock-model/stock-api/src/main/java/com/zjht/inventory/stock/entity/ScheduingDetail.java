package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/18.
 */

import java.util.Date;

/**
 * 出/入库明细
 */
public class ScheduingDetail {

    /**
     * 全参构造方法
     * @param id
     * @param systrace
     * @param materielId
     * @param inventoryDetailedId
     * @param operateNum
     */
    public ScheduingDetail(Long id, String systrace, Long  materielId,
                           Long inventoryDetailedId, Long operateNum) {
        this.id = id;
        this.systrace = systrace;
        this.materielId = materielId;
        this.inventoryDetailedId = inventoryDetailedId;
        this.operateNum = operateNum;
    }

    /**
     * 明细编号
     */
    private Long id;
    /**
     * 系统流水号
     */
    private String systrace;
    /**
     * 物料编号
     */
    private Long materielId;
    /**
     * 库存明细编号
     */
    private Long inventoryDetailedId;
    /**
     * 操作数量
     */
    private Long operateNum;

    public ScheduingDetail() {
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
        return "ScheduingDetail{" +
                "id='" + id + '\'' +
                ", systrace='" + systrace + '\'' +
                ", materielId='" + materielId + '\'' +
                ", inventoryDetailedId='" + inventoryDetailedId + '\'' +
                ", operateNum='" + operateNum + '\'' +
                '}';
    }
}
