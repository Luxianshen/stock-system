package com.zjht.inventory.extensions.webinterface.ui.entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Jason on 2016/10/20.
 */
public class ApplyListPropertyEntity {
    /**
     * 属性清单编号
     */
    @NotEmpty(message = "属性编号不能为空")
    private String id ;
    /**
     * 清单编号
     */
    @NotEmpty(message = "清单编号不能为空")
    private String listId ;
    /**
     * 属性名
     */
    @NotEmpty(message = "属性名不能为空")
    private String property ;
    /**
     * 属性值
     */
    @NotEmpty(message = "属性值不能为空")
    private String value ;

    public ApplyListPropertyEntity() {
    }

    public ApplyListPropertyEntity(String id, String listId, String property, String value) {
        this.id = id;
        this.listId = listId;
        this.property = property;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ApplyListPropertyEntity{" +
                "id='" + id + '\'' +
                ", listId='" + listId + '\'' +
                ", property='" + property + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
