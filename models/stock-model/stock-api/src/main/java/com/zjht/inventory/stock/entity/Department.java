package com.zjht.inventory.stock.entity;

import java.util.Date;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public class Department {

    /**
     * 全参构造
     *
     * @param id
     * @param code           部门代码
     * @param name           部门名称
     * @param description    部门简介
     * @param superCode     上级部门代码
     * @param companyCode   所属公司
     * @param employeeCode  负责人
     * @param createBy      创建人
     * @param updateBy      更新人
     * @param createTime    创建时间
     * @param updateTime    更新时间
     * @param status        状态
     * @param memo          备注
     * @param ts01          时间戳
     * @param centerCode    所属中心
     * @param effectiveDate 生效时间
     **/


    private Number id;
    private String code;
    private String name;
    private String description;
    private String superCode;
    private String companyCode;
    private String employeeCode;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String status;
    private String memo;
    private String ts01;
    private String centerCode;
    private Date effectiveDate;

    public Department() {
    }

    public Department(Number id, String code, String name, String description, String superCode, String companyCode, String employeeCode, String createBy, String updateBy, Date createTime, Date updateTime, String status, String memo, String ts01, String centerCode, Date effectiveDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.superCode = superCode;
        this.companyCode = companyCode;
        this.employeeCode = employeeCode;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.memo = memo;
        this.ts01 = ts01;
        this.centerCode = centerCode;
        this.effectiveDate = effectiveDate;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperCode() {
        return superCode;
    }

    public void setSuperCode(String superCode) {
        this.superCode = superCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTs01() {
        return ts01;
    }

    public void setTs01(String ts01) {
        this.ts01 = ts01;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}



