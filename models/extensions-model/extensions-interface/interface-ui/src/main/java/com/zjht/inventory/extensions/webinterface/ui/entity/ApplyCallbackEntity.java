package com.zjht.inventory.extensions.webinterface.ui.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Jason on 2016/11/1.
 */
public class ApplyCallbackEntity {

    /**
     * 申请回调实体全参
     * @param applyId
     * @param handleBy
     * @param handleResult
     * @param handleRemark
     */
    public ApplyCallbackEntity(String applyId, String handleBy,
                               String handleResult, String handleRemark) {
        this.applyId = applyId;
        this.handleBy = handleBy;
        this.handleResult = handleResult;
        this.handleRemark = handleRemark;
    }

    /**
     * 申请编号
     */
    private String applyId;
    /**
     * 处理人
     */
    private String handleBy;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 处理说明
     */
    private String handleRemark;
    /**
     * 操作物料集合
     * 物料编号,资产编号集合
     */
    private LinkedHashMap<String,MaterialInfo> materialInfoMap;

    public ApplyCallbackEntity() {
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getHandleBy() {
        return handleBy;
    }

    public void setHandleBy(String handleBy) {
        this.handleBy = handleBy;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public LinkedHashMap<String, MaterialInfo> getMaterialInfoMap() {
        return materialInfoMap;
    }

    public void setMaterialInfoMap(LinkedHashMap<String, MaterialInfo> materialInfoMap) {
        this.materialInfoMap = materialInfoMap;
    }

    @Override
    public String toString() {
        return "ApplyCallbackEntity{" +
                "applyId='" + applyId + '\'' +
                ", handleBy='" + handleBy + '\'' +
                ", handleResult='" + handleResult + '\'' +
                ", handleRemark='" + handleRemark + '\'' +
                ", materialInfoMap=" + materialInfoMap +
                '}';
    }
}
