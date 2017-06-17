package com.zjht.inventory.extensions.webinterface.ui.entity;

import org.hibernate.validator.constraints.NotEmpty;
import sun.misc.Regexp;

import javax.validation.constraints.Pattern;

/**
 * Created by Jason on 2016/10/20.
 */
public class ApplyListEntity {
    /**
     * 清单编号
     */
    @NotEmpty(message = "清单编号不能为空")
    private String id ;
    /**
     * 物料分类编号
     */
    @NotEmpty(message = "分类编号不能为空")
    private String materielTypeId ;
    /**
     * 物料编号
     */
    @NotEmpty(message = "物料编号不能为空")
    private String materielId ;
    /**
     * 申请编号
     */
    @NotEmpty(message = "申请编号不能为空")
    private String applyId ;
    /**
     * 申请数量
     */
    private long count ;

    public ApplyListEntity() {
    }

    public ApplyListEntity(String id, String materielTypeId, String materielId, String applyId, long count) {
        this.id = id;
        this.materielTypeId = materielTypeId;
        this.materielId = materielId;
        this.applyId = applyId;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterielTypeId() {
        return materielTypeId;
    }

    public void setMaterielTypeId(String materielTypeId) {
        this.materielTypeId = materielTypeId;
    }

    public String getMaterielId() {
        return materielId;
    }

    public void setMaterielId(String materielId) {
        this.materielId = materielId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ApplyListEntity{" +
                "id='" + id + '\'' +
                ", materielTypeId='" + materielTypeId + '\'' +
                ", materielId='" + materielId + '\'' +
                ", applyId='" + applyId + '\'' +
                ", count=" + count +
                '}';
    }
}
