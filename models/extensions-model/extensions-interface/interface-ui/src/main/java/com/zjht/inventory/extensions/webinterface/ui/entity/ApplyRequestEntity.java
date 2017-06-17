package com.zjht.inventory.extensions.webinterface.ui.entity;

/**
 * Created by Jason on 2016/10/20.
 */

import java.util.ArrayList;

/**
 * 请求实体
 */
public class ApplyRequestEntity {
    /**
     * 申请表
     */
    private ApplyEntity apply;
    /**
     * 申请清单表
     */
    private ArrayList<ApplyListEntity> applyLists;
    /**
     * 申请清单属性表
     */
    private ArrayList<ApplyListPropertyEntity> applyListProperties;


    public ApplyRequestEntity() {
    }


    public ApplyEntity getApply() {
        return apply;
    }

    public void setApply(ApplyEntity apply) {
        this.apply = apply;
    }

    public ArrayList<ApplyListEntity> getApplyLists() {
        return applyLists;
    }

    public void setApplyLists(ArrayList<ApplyListEntity> applyLists) {
        this.applyLists = applyLists;
    }

    public ArrayList<ApplyListPropertyEntity> getApplyListProperties() {
        return applyListProperties;
    }

    public void setApplyListProperties(ArrayList<ApplyListPropertyEntity> applyListProperties) {
        this.applyListProperties = applyListProperties;
    }

    @Override
    public String toString() {
        return "ApplyRequestEntity{" +
                "apply=" + apply +
                ", applyLists=" + applyLists +
                ", applyListProperties=" + applyListProperties +
                '}';
    }
}
