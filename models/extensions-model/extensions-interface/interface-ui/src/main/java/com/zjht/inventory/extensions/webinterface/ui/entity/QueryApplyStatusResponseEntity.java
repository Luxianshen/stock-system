package com.zjht.inventory.extensions.webinterface.ui.entity;

/**
 * Created by Jason on 2016/11/1.
 */
public class QueryApplyStatusResponseEntity extends AbstractResponseEntity {
    /**
     * 申请单状态
     */
    private String status;

    /**
     * 状态说明
     */
    private String statusRemark;

    public QueryApplyStatusResponseEntity(String responseCode) {
        super(responseCode);
    }

    public QueryApplyStatusResponseEntity(String responseCode, String status, String statusRemark) {
        super(responseCode);
        this.status = status;
        this.statusRemark = statusRemark;
    }

    public QueryApplyStatusResponseEntity(String responseCode, String prompt,
                                          String status, String statusRemark) {
        super(responseCode, prompt);
        this.status = status;
        this.statusRemark = statusRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
    }

    @Override
    public String toString() {
        return "QueryApplyStatusResponseEntity{" +
                "status='" + status + '\'' +
                ", statusRemark='" + statusRemark + '\'' +
                ", "+super.toString() +
                '}';
    }
}
