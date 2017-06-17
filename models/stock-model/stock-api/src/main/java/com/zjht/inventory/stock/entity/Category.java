package com.zjht.inventory.stock.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jason on 2016/10/24。
 */
public class Category implements Serializable {
    /**
     * 主键。
     */
    private Long id;
    /**
     * 上级。
     */
    private Long superiorId;
    /**
     * 分类代码。
     */
    private String code;
    /**
     * 分类名称。
     */
    private String name;
    /**
     * 分类编码。
     */
    private String encoder;
    /**
     * 逻辑删除。
     */
    private Long isdel;

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category() {
    }

    public Category(Long id, Long superiorId, String code, String name) {
        this.id = id;
        this.superiorId = superiorId;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", superiorId=" + superiorId +
                ", encoder='" + encoder + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }

    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }

    public Long getIsdel() {
        return isdel;
    }

    public void setIsdel(Long isdel) {
        this.isdel = isdel;
    }
}
