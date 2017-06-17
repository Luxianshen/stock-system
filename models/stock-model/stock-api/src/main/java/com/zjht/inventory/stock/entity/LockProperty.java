package com.zjht.inventory.stock.entity;

import java.io.Serializable;

/**
 * Created by Jason on 2016/11/11.
 */
public class LockProperty extends Property implements Serializable {
    /**
     * 全参构造
     *
     * @param id
     * @param lockId
     * @param property
     * @param value
     */
    public LockProperty(Long id, Long lockId, String property, String value) {
        super(property, value);
        this.id = id;
        this.lockId = lockId;
    }

    public LockProperty() {
        super();
    }

    /**
     * 锁定属性主键
     */
    private Long id;
    /**
     * 锁定属性主键
     */
    private Long lockId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }

    @Override
    public String toString() {
        return "LockProperty{" + super.toString() + "id=" + id + ", lockId=" + lockId + '}';
    }
}
