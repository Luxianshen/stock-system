package com.zjht.inventory.stock.entity;

import java.util.Date;

public class CheckPlanDto {
	/** 主键 */
	private Long id;
	/** 创建时间 */
	private Date createTime;
	/** 计划名称 */
	private String planName;
	/** 盘点仓库主键 */
	private Long checkWarehouse;
	/** 盘点比例 */
	private Number checkProportion;
	/** 盘点物料 */
	private String checkMaterial;
	/** 应当盘点数量 */
	private Number shouldCheckNum;
	/** 盘点人主键 */
	private String checkBy;
	/** 盘点时间 */
	private Date checkTime;
	/** 实际盘点数量 */
	private Number actualCheckNum;
	/** 仓库名称 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Long getCheckWarehouse() {
		return checkWarehouse;
	}

	public void setCheckWarehouse(Long checkWarehouse) {
		this.checkWarehouse = checkWarehouse;
	}

	public Number getCheckProportion() {
		return checkProportion;
	}

	public void setCheckProportion(Number checkProportion) {
		this.checkProportion = checkProportion;
	}

	public String getCheckMaterial() {
		return checkMaterial;
	}

	public void setCheckMaterial(String checkMaterial) {
		this.checkMaterial = checkMaterial;
	}

	public Number getShouldCheckNum() {
		return shouldCheckNum;
	}

	public void setShouldCheckNum(Number shouldCheckNum) {
		this.shouldCheckNum = shouldCheckNum;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Number getActualCheckNum() {
		return actualCheckNum;
	}

	public void setActualCheckNum(Number actualCheckNum) {
		this.actualCheckNum = actualCheckNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CheckPlanDto [id=" + id + ", createTime=" + createTime + ", planName=" + planName + ", checkWarehouse="
				+ checkWarehouse + ", checkProportion=" + checkProportion + ", checkMaterial=" + checkMaterial
				+ ", shouldCheckNum=" + shouldCheckNum + ", checkBy=" + checkBy + ", checkTime=" + checkTime
				+ ", actualCheckNum=" + actualCheckNum + ", name=" + name + "]";
	}

}
