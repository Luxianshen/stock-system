package com.zjht.inventory.extensions.webinterface.ui.entity;

/**
 * Created by Jason on 2016/10/21.
 */
public class MaterialEntity {
    /**
     * 物料编号
     */
    private String id ;
    /**
     * 物料代码
     */
    private String code ;
    /**
     * 物料名称
     */
    private String name ;
    /**
     * 类型
     */
    private String type ;
    /**
     * 创建方式
     */
    private String createTime ;

    public MaterialEntity() {
    }

    public MaterialEntity(String id, String code, String name, String type, String createTime) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaterialEntity{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
