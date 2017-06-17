package com.zjht.inventory.stock.entity;

import java.util.ArrayList;

/**
 * Created by Jason on 2016/11/1.
 */
public class MaterialInfo {


    /**
     * 计件使用
     * @param assetsNos
     */
    public MaterialInfo(ArrayList<String> assetsNos) {
        this.assetsNos = assetsNos;
    }

    /**
     * 计量使用
     * @param operateNum
     */
    public MaterialInfo(Long operateNum) {
        this.operateNum = operateNum;
    }

    /**
     * 资产编号集合
     * @计件物品有效
     */
    private ArrayList<String> assetsNos;
    /**
     * 操作数量
     * @计量物品有效
     */
    private Long operateNum;

    public MaterialInfo() {
    }

    public ArrayList<String> getAssetsNos() {
        return assetsNos;
    }

    public void setAssetsNos(ArrayList<String> assetsNos) {
        this.assetsNos = assetsNos;
    }

    public Long getOperateNum() {
        return operateNum;
    }

    public void setOperateNum(Long operateNum) {
        this.operateNum = operateNum;
    }

    @Override
    public String toString() {
        return "MaterialInfo{" +
                "assetsNos=" + assetsNos +
                ", operateNum=" + operateNum +
                '}';
    }
}
