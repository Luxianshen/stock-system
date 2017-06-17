package com.zjht.inventory.stock.entity;

/**
 * Created by longshuzhen on 2016/10/20.
 */
public class Manager {
    /**
     * 员工编号
     */
    private String ID;
    /**
     * 员工名字
     */
    private String NAME;

    public Manager() {
    }

    public Manager(String ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
