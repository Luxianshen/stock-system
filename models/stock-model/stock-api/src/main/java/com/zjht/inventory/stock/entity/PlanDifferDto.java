package com.zjht.inventory.stock.entity;

import java.util.Date;
import java.util.List;

public class PlanDifferDto {
	/** 主键 */
	private Long id;
	/** 计划时间 */
	private Date planTime;
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

	private List<WarehouseInventory> warehouseInventory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWarehouseInventory(List<WarehouseInventory> warehouseInventory) {
		this.warehouseInventory = warehouseInventory;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
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

	public List<WarehouseInventory> getWarehouseInventory() {
		return warehouseInventory;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "PlanDifferDto [id=" + id + ", planTime=" + planTime + ", planName=" + planName + ", checkWarehouse="
				+ checkWarehouse + ", checkProportion=" + checkProportion + ", checkMaterial=" + checkMaterial
				+ ", shouldCheckNum=" + shouldCheckNum + ", checkBy=" + checkBy + ", checkTime=" + checkTime
				+ ", actualCheckNum=" + actualCheckNum + ", warehouseInvertory=" + (warehouseInventory != null
						? warehouseInventory.subList(0, Math.min(warehouseInventory.size(), maxLen)) : null)
				+ "]";
	}

}
