package com.zjht.inventory.extensions.webinterface.ui.entity;

import java.util.Map;

/**
 * Created by Jason on 2016/10/20.
 */
public class ApplyResponseEntity extends AbstractResponseEntity{
    /**
     * 系统申请编号
     */
    private String applyId;

    /**
     * 清单
     * String: 物料名
     * Long: 数量
     */
    private Map<String,Long> data;

    public ApplyResponseEntity(String responseCode) {
        super(responseCode);
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Map<String, Long> getData() {
        return data;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }



    public ApplyResponseEntity(String responseCode, String applyId, Map<String, Long> data) {
        super(responseCode);
        this.applyId = applyId;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApplyResponseEntity{" +
                "applyId='" + applyId + '\'' +
                ", data=" + data +
                '}';
    }
}
