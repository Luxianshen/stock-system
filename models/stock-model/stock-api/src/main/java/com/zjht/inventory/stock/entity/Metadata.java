package com.zjht.inventory.stock.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * 元数据。
 */
public class Metadata implements Serializable {

    private Long                           id;
    private String                         name; //属性名
    private String                         code; //属性代码
    private String                         dataType; //数据类型
    private Long                           length;   //数据长度
    private Long                           materielId; //物料主键
    private String                         dictionaryCode; //物料字典主键
    private ArrayList<Map<String, String>> items;
    private String                         remark;  //物料备注
    private Long                           statistics;  //是否参与统计
    private Long                           require;     //是否必要
    private Long                           uniqued;    //唯一校验字段

    public Metadata(
        String name, String code, String dataType, long length, String dictionaryCode) {
        this.name = name;
        this.code = code;
        this.dataType = dataType;
        this.length = length;
        this.dictionaryCode = dictionaryCode;
    }

    public Metadata() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public ArrayList<Map<String, String>> getItems() {
        return items;
    }

    public void setItems(ArrayList<Map<String, String>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Metadata{" + "id=" + id + ", name='" + name + '\'' + ", code='" + code + '\'' +
               ", dataType='" + dataType + '\'' + ", length=" + length + ", materielId=" +
               materielId + ", dictionary_code='" + dictionaryCode + '\'' + ", items=" + items +
               ", remark='" + remark + '\'' + ", statistics=" + statistics + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Long materielId) {
        this.materielId = materielId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStatistics() {
        return statistics;
    }

    public void setStatistics(Long statistics) {
        this.statistics = statistics;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public Long getRequire() {
        return require;
    }

    public void setRequire(Long require) {
        this.require = require;
    }

    public Long getUniqued() {
        return uniqued;
    }

    public void setUniqued(Long uniqued) {
        this.uniqued = uniqued;
    }
}
