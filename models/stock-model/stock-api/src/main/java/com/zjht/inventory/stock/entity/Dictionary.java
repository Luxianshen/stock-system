package com.zjht.inventory.stock.entity;

import java.io.Serializable;

/**
 * Created by lujiasheng on 2016/10/31。
 */
public class Dictionary implements Serializable {

    /**
     * 表名 T_DATA_DICTIONARY_ITEM。
     * 主键 id
     * 代码 code
     * 名字 name
     * 选项名 optionName
     * 选项值 optionValue
     * 创建/更新储存数据 json
     */

    private Long id;
    private String code;
    private String name;
    private String optionName;
    private String optionValue;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
