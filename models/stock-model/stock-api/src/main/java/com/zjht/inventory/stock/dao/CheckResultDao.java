package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.CheckResult;

public interface CheckResultDao {

	/**
	 * 根据盘点计划ID查询盘点结果
	 * 
	 * @param planId
	 * @return
	 */
	public CheckResult findCheckResultByPlanId(String planId);

}
