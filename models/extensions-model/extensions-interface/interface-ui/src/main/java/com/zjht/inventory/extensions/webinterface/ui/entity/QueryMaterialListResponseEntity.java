package com.zjht.inventory.extensions.webinterface.ui.entity;

import java.util.ArrayList;

/**
 * Created by Jason on 2016/10/20.
 */
public class QueryMaterialListResponseEntity extends AbstractResponseEntity{

    /**
     * 物料列表
     */
    private ArrayList<MaterialEntity> data ;

    public QueryMaterialListResponseEntity(String responseCode, String prompt, ArrayList<MaterialEntity> data) {
        super(responseCode, prompt);
        this.data = data;
    }

    public QueryMaterialListResponseEntity(String responseCode) {
        super(responseCode);
    }

    public QueryMaterialListResponseEntity(String responseCode, String prompt) {
        super(responseCode, prompt);
    }

    public QueryMaterialListResponseEntity(String code, ArrayList<MaterialEntity> data) {
        super(code);
        this.data = data;
    }

    public ArrayList<MaterialEntity> getData() {
        return data;
    }

    public void setData(ArrayList<MaterialEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QueryMaterialListResponseEntity{" +
                "data=" + data +
                '}';
    }
}
