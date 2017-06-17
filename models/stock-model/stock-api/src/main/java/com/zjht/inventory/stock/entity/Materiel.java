package com.zjht.inventory.stock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lujiasheng on 2016/10/18。
 */
public class Materiel implements Serializable {

    public static final Long QUANTITATIVE = 1L;
    public static final Long PICEC        = 2L;

    /**
     * 实体说明 id 主键 code 物料代码 name 物料名 materielCategoryId 物料分类主键 type 物料类型。
     * mappingPrivateTable 对应私有表名 createTime 创建时间 json 用作传递数据
     */

    private Long   id;
    private String code;
    private String name;
    private Long   materielCategoryId;
    private Long   type;
    private String mappingPrivateTable;
    private Date   createTime;
    private Long   statistics;
    private Long   require;
    private String json;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getMaterielCategoryId() {
        return materielCategoryId;
    }

    public void setMaterielCategoryId(Long materielCategoryId) {
        this.materielCategoryId = materielCategoryId;
    }

    public String getMappingPrivateTable() {
        return mappingPrivateTable;
    }

    public void setMappingPrivateTable(String mappingPrivateTable) {
        this.mappingPrivateTable = mappingPrivateTable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }


    public Long getStatistics() {
        return statistics;
    }

    public void setStatistics(Long statistics) {
        this.statistics = statistics;
    }

    public Long getRequire() {
        return require;
    }

    public void setRequire(Long require) {
        this.require = require;
    }
}
