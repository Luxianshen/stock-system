package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/19.
 */

import java.io.Serializable;

/**
 * 申请属性清单
 */
public class ApplyListProperty extends Property implements Serializable {

    public static final String SEQ = "T_LOCK_PROPERTY_SEQ";

    /**
     * 全参构造
     * @param id
     * @param listId
     * @param property
     * @param value
     */
    public ApplyListProperty(Long id, Long listId, String property, String value) {
        super(property, value);
        this.id = id;
        this.listId = listId;
    }

    /**
     * 属性清单编号
     */
    private Long id ;
    /**
     * 清单编号
     */
    private Long listId ;
    /**
     * 属性名
     */
    private String propertyName ;

    public ApplyListProperty() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toString() {
        return "ApplyListProperty{" +
                "id=" + id +
                ", listId=" + listId +
                ", propertyName='" + propertyName + '\'' +
                super.toString()+
                '}';
    }
}
