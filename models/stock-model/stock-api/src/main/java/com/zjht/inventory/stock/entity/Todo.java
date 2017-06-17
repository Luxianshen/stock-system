package com.zjht.inventory.stock.entity;

import java.util.Date;

/**
 * Created by lujiasheng on 2016/10/19.
 */
public class Todo {

    /**
     * ID 主键
     * MATTER_DESCRIBE 事项描述
     * CREATE_TIME 创建时间
     * HANDLE_BY 处理人主键
     * HANDLE_TIME 处理时间
     * HANDLE_RESULT 处理结果
     * HANDLE_REMARK 处理说明
     */

    private String ID;
    private String MATTER_DESCRIBE;
    private Date CREATE_TIME;
    private  String HANDLE_BY;
    private Date HANDLE_TIME;
    private  Long HANDLE_RESULT;
    private String HANDLE_REMARK;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMATTER_DESCRIBE() {
        return MATTER_DESCRIBE;
    }

    public void setMATTER_DESCRIBE(String MATTER_DESCRIBE) {
        this.MATTER_DESCRIBE = MATTER_DESCRIBE;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getHANDLE_BY() {
        return HANDLE_BY;
    }

    public void setHANDLE_BY(String HANDLE_BY) {
        this.HANDLE_BY = HANDLE_BY;
    }

    public Date getHANDLE_TIME() {
        return HANDLE_TIME;
    }

    public void setHANDLE_TIME(Date HANDLE_TIME) {
        this.HANDLE_TIME = HANDLE_TIME;
    }

    public Long getHANDLE_RESULT() {
        return HANDLE_RESULT;
    }

    public void setHANDLE_RESULT(Long HANDLE_RESULT) {
        this.HANDLE_RESULT = HANDLE_RESULT;
    }

    public String getHANDLE_REMARK() {
        return HANDLE_REMARK;
    }

    public void setHANDLE_REMARK(String HANDLE_REMARK) {
        this.HANDLE_REMARK = HANDLE_REMARK;
    }
}
