package com.zjht.inventory.stock.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jason on 2016/11/3 0003.
 */
public class MaterialInfoVO {

    private String applyId;

    private ArrayList<Map<String,String>> maps;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public ArrayList<Map<String, String>> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<Map<String, String>> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "MaterialInfoVO{" +
                "applyId='" + applyId + '\'' +
                ", maps=" + maps +
                '}';
    }
}
