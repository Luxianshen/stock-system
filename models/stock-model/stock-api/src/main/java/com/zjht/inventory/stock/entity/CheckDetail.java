package com.zjht.inventory.stock.entity;

import java.util.Date;

public class CheckDetail {

	/** 主键 */
	private Long id;
	/** 盘点结果主键 */
	private Long checkResultId;
	/**
	 * 差异类型: 1.不在系统 2.不再仓库 3.状态不实 4.正常
	 */
	private Number differType;
	/** 物料主键 */
	private Long materielId;
	/** 明细主键 */
	private Long detailedId;
	/** 系统状态 */
	private Number systemStatus;
	/** 盘点状态 */
	private Number checkStatus;
	/** 处理人主键 */
	private String handleBy;
	/** 处理时间 */
	private Date handleTime;
	/**
	 * 处理结果: 1.未处理 2.已处理-盘点入库 3.已处理-变更状态 4.无差异
	 */
	private Number handleResult;
	/** 处理说明 */
	private String handleRemark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Number getDifferType() {
		return differType;
	}

	public void setDifferType(Number differType) {
		this.differType = differType;
	}

	public Long getCheckResultId() {
		return checkResultId;
	}

	public void setCheckResultId(Long checkResultId) {
		this.checkResultId = checkResultId;
	}

	public Long getMaterielId() {
		return materielId;
	}

	public void setMaterielId(Long materielId) {
		this.materielId = materielId;
	}

	public Long getDetailedId() {
		return detailedId;
	}

	public void setDetailedId(Long detailedId) {
		this.detailedId = detailedId;
	}

	public Number getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(Number systemStatus) {
		this.systemStatus = systemStatus;
	}

	public Number getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Number checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getHandleBy() {
		return handleBy;
	}

	public void setHandleBy(String handleBy) {
		this.handleBy = handleBy;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Number getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(Number handleResult) {
		this.handleResult = handleResult;
	}

	public String getHandleRemark() {
		return handleRemark;
	}

	public void setHandleRemark(String handleRemark) {
		this.handleRemark = handleRemark;
	}

	@Override
	public String toString() {
		return "CheckDetail [id=" + id + ", checkResultId=" + checkResultId + ", differType=" + differType
				+ ", materielId=" + materielId + ", detailedId=" + detailedId + ", systemStatus=" + systemStatus
				+ ", checkStatus=" + checkStatus + ", handleBy=" + handleBy + ", handleTime=" + handleTime
				+ ", handleResult=" + handleResult + ", handleRemark=" + handleRemark + "]";
	}

}
