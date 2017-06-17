package com.zjht.inventory.stock.entity;

import java.util.Date;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public class Company {
    /**
     * 全参构造
     *
     * @param id
     * @param code 公司代码
     * @param name 公司名称
     * @param address 公司地址
     * @param rep    法人代表
     * @param employeeCode  总经理
     * @param fax  传真号码
     * @param tel  联系电话
     * @param taxNo    税号
     * @param bank    开户银行
     * @param account  帐号
     * @param zip      邮政编码
     * @param superCode  上级代码
     * @param createTime 创建时间
     * @param createBy  创建人
     * @param updateTime 更新时间
     * @param updateBy  更新人
     * @param status   状态
     * @param memo    备注
     * @param ts01    时间戳
     * @param effectiveDate 生效时间
     */
    private Number id;
    private String code;
    private String name;
    private String address;
    private String rep;
    private String employeeCode;
    private String fax;
    private String tel;
    private String taxNo;
    private String bank;
    private String account;
    private String zip;
    private String superCode;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String status;
    private String memo;
    private String ts01;
    private Date effectiveDate;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSuperCode() {
        return superCode;
    }

    public void setSuperCode(String superCode) {
        this.superCode = superCode;
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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Company() {
    }

    public Company(Number id, String code, String name, String address, String rep, String employeeCode, String fax, String tel, String taxNo, String bank, String account, String zip, String superCode, String createBy, String updateBy, Date createTime, Date updateTime, String status, String memo, String ts01, Date effectiveDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.rep = rep;
        this.employeeCode = employeeCode;
        this.fax = fax;
        this.tel = tel;
        this.taxNo = taxNo;
        this.bank = bank;
        this.account = account;
        this.zip = zip;
        this.superCode = superCode;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.memo = memo;
        this.ts01 = ts01;
        this.effectiveDate = effectiveDate;
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rep='" + rep + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                ", fax='" + fax + '\'' +
                ", tel='" + tel + '\'' +
                ", taxNo='" + taxNo + '\'' +
                ", bank='" + bank + '\'' +
                ", account='" + account + '\'' +
                ", zip='" + zip + '\'' +
                ", superCode='" + superCode + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", memo='" + memo + '\'' +
                ", ts01='" + ts01 + '\'' +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
