package com.zjht.inventory.stock.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by Jason on 2016/11/1.
 */

/**
 * 回调运营中端的接口实体
 */
public class ApplyCallbackEntity implements Serializable {

    private static final long version = 1L;

    /**
     * 申请回调实体全参
     * @param serialNumber
     * @param handleBy
     * @param handleResult
     * @param handleRemark
     */
    public ApplyCallbackEntity(String serialNumber, String handleBy,
                               Long handleResult, String handleRemark) {
        this.applyId = serialNumber;
        this.handleBy = handleBy;
        this.handleResult = handleResult;
        this.handleRemark = handleRemark;
    }

    /**
     * 申请编号
     */
    @NotEmpty(message = "申请编号不能为空")
    @Pattern(regexp = "^[1-9]\\d*$", message = "申请编号是纯数字组成")
    private String applyId;
    /**
     * 处理人
     */
    @NotEmpty(message = "处理人不能为空")
    private String handleBy;
    /**
     * 处理结果
     */
    @NotEmpty(message = "处理结果不能为空")
    private Long handleResult;
    /**
     * 处理说明
     */
    @NotEmpty(message = "处理说明不能为空")
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

    public Long getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Long handleResult) {
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
