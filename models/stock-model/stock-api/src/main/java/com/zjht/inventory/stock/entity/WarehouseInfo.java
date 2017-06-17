package com.zjht.inventory.stock.entity;

/**
 * Created by longshuzhen on 2016/10/18.
 */

import java.util.Date;

/**
 * 仓库信息表
 */
public class WarehouseInfo {

    /**
     * 仓库编号
     */
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库所属机构
     */
    private String organBelong;

    /**
     * 仓库所在地址
     */
    private String address;

    /**
     * 仓库管理员主键
     */
    private String warehouseKeeper;

    /**
     * 仓库创建时间
     */
    private Date createTime;

    /**
     * 仓库说明
     */
    private String remark;

    /**
     * 仓库状态
     *     1、已撤销
     *     2、正常
     */
    private String status;


    public WarehouseInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganBelong() {
        return organBelong;
    }

    public void setOrganBelong(String organBelong) {
        this.organBelong = organBelong;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(String warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public WarehouseInfo(Long id, String name, String organBelong, String address, String warehouseKeeper, Date createTime, String remark, String status) {
        this.id = id;
        this.name = name;
        this.organBelong = organBelong;
        this.address = address;
        this.warehouseKeeper = warehouseKeeper;
        this.createTime = createTime;
        this.remark = remark;
        this.status = status;
    }

    @Override
    public String toString() {
        return "WarehouseInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organBelong='" + organBelong + '\'' +
                ", address='" + address + '\'' +
                ", warehouseKeeper='" + warehouseKeeper + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
