package com.zjht.inventory.stock.entity;

import java.util.Date;

public class CheckResult {

	/** 主键 */
	private Long id;
	/** 盘点计划主键 */
	private Long planId;
	/** 实际盘点数量 */
	private Number actualCheckNum;
	/** 盘点人主键 */
	private String checkBy;
	/** 盘点时间 */
	private Date checkTime;
	/**
	 * 盘点结果: 1.无差异 2.有差异
	 */
	private Number checkResult;
	/** 盘点说明 */
	private String checkRemark;
	/** 盘点状态 */
	private Number checkStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Number getActualCheckNum() {
		return actualCheckNum;
	}

	public void setActualCheckNum(Number actualCheckNum) {
		this.actualCheckNum = actualCheckNum;
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

	public Number getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Number checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	public Number getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Number checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return "CheckResult [id=" + id + ", planId=" + planId + ", actualCheckNum=" + actualCheckNum + ", checkBy="
				+ checkBy + ", checkTime=" + checkTime + ", checkResult=" + checkResult + ", checkRemark=" + checkRemark
				+ ", checkStatus=" + checkStatus + "]";
	}

}
