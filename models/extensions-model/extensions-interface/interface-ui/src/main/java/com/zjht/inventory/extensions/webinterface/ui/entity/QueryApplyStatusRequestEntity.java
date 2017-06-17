package com.zjht.inventory.extensions.webinterface.ui.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Jason on 2016/11/1.
 */
public class QueryApplyStatusRequestEntity {

    /**
     * 申请编号
     */
    @NotEmpty(message = "申请编号不能为空")
    private String applyId;

    public QueryApplyStatusRequestEntity() {
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    @Override
    public String toString() {
        return "QueryApplyStatusRequestEntity{" +
                "applyId='" + applyId + '\'' +
                '}';
    }
}
