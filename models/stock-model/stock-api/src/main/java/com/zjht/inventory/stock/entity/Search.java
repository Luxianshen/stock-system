package com.zjht.inventory.stock.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 搜索实体
 */
public class Search {
    /**
     * 参数集
     */
    private ArrayList<Map<String,Object>> maps;

    public ArrayList<Map<String, Object>> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<Map<String, Object>> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Search{" +
                "maps=" + maps +
                '}';
    }
}
