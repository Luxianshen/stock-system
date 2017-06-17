package com.zjht.inventory.stock.entity;

import java.io.Serializable;

/**
 * Created by Jason on 2016/11/11.
 */
public class Property implements Serializable {
    /**
     * 全参
     *
     * @param property
     * @param value
     */
    public Property(String property, String value) {
        this.property = property;
        this.value = value;
    }

    private String property;

    private String value;

    public Property() {
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
        return "Property{" + "property='" + property + '\'' + ", value='" + value + '\'' + '}';
    }
}
