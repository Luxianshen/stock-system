package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/18.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 库存锁定实体
 */
public class Lock implements Serializable {
    public static final String SEQ = "T_LOCK_SEQ";
    /**
     * 主键
     */
    private Long id;
    /**
     * 物料分类主键
     */
    private Long materielTypeId;
    /**
     * 物料主键
     */
    private Long materielId;
    /**
     * 锁定数量
     */
    private Long lockNum;
    /**
     * 申请编号
     */
    private Long applyId;
    /**
     * 目标仓库
     */
    private Long warehouseId;

    public Lock() {
    }

    /**
     * 锁定属性
     */
    private ArrayList<LockProperty> lockProperties;

    /**
     * 申请锁定
     *
     * @param applyId
     */
    public Lock(Long id, Long applyId, Long warehouseId, ApplyList applyList) {
        this.id = id;
        this.materielTypeId = applyList.getMaterielTypeId();
        this.materielId = applyList.getMaterielId();
        this.lockNum = applyList.getCount();
        this.applyId = applyId;
        this.warehouseId = warehouseId;
        this.lockProperties = getLockProperties(id, applyList);
    }

    // 丄丄丄丄丄丄丄丄丄丄丄 用于申请锁定构造方法拼装属性实体 丄丄丄丄丄丄丄丄丄丄丄
    private ArrayList<LockProperty> getLockProperties(Long lockId, ApplyList applyList) {
        ArrayList<LockProperty> list = new ArrayList<>();
        try {
            for (ApplyListProperty alp : applyList.getApplyListProperties()) {
                list.add(new LockProperty(alp.getId(), lockId, alp.getProperty(), alp.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list != null ? list : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterielTypeId() {
        return materielTypeId;
    }

    public void setMaterielTypeId(Long materielTypeId) {
        this.materielTypeId = materielTypeId;
    }

    public Long getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Long materielId) {
        this.materielId = materielId;
    }

    public Long getLockNum() {
        return lockNum;
    }

    public void setLockNum(Long lockNum) {
        this.lockNum = lockNum;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public ArrayList<LockProperty> getLockProperties() {
        return lockProperties;
    }

    public void setLockProperties(ArrayList<LockProperty> lockProperties) {
        this.lockProperties = lockProperties;
    }

    @Override
    public String toString() {
        return "Lock{" + "id=" + id + ", materielTypeId=" + materielTypeId + ", materielId=" +
               materielId + ", lockNum=" + lockNum + ", applyId=" + applyId + ", warehouseId=" +
               warehouseId + ", lockProperties=" + lockProperties + '}';
    }
}
