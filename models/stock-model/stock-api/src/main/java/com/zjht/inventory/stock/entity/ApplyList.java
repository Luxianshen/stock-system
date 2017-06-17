package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/19.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 申请清单
 */
public class ApplyList implements Serializable {

    private static final long version = 1L;

    public static final String SEQ = "T_APPLY_LIST_SEQ";

    /**
     * 全参构造
     * @param id
     * @param materielTypeId
     * @param materielId
     * @param applyId
     * @param count
     */
    public ApplyList(Long id, Long materielTypeId, Long materielId, Long applyId, long count) {
        this.id = id;
        this.materielTypeId = materielTypeId;
        this.materielId = materielId;
        this.applyId = applyId;
        this.count = count;
    }

    /**
     * 清单编号
     */
    private Long id ;
    /**
     * 物料分类编号
     */
    private Long materielTypeId ;
    private String materielTypeName ;
    /**
     * 物料编号
     */
    private Long materielId ;
    private String materielName ;
    /**
     * 申请编号
     */
    private Long applyId ;
    /**
     * 申请数量
     */
    private long count ;
    /**
     * 申请数量
     */
    private ArrayList<ApplyListProperty> applyListProperties ;

    public ArrayList<ApplyListProperty> getApplyListProperties() {
        return applyListProperties;
    }

    public void setApplyListProperties(ArrayList<ApplyListProperty> applyListProperties) {
        this.applyListProperties = applyListProperties;
    }

    public ApplyList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterielTypeId() {
        return materielTypeId;
    }

    public void setMaterielTypeId(Long materielTypeId) {
        this.materielTypeId = materielTypeId;
    }

    public String getMaterielTypeName() {
        return materielTypeName;
    }

    public void setMaterielTypeName(String materielTypeName) {
        this.materielTypeName = materielTypeName;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public Long getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Long materielId) {
        this.materielId = materielId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ApplyList{" +
                "id='" + id + '\'' +
                ", materielTypeId='" + materielTypeId + '\'' +
                ", materielId='" + materielId + '\'' +
                ", applyId='" + applyId + '\'' +
                ", count=" + count +
                ", applyListProperties=" + applyListProperties +
                '}';
    }
}
