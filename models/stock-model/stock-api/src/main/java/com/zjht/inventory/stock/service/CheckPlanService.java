package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.CheckPlan;

public interface CheckPlanService {

	/**
	 * 创建盘点计划
	 * 
	 * @param checkPlan
	 */
	public void createCheckPlan(CheckPlan checkPlan);

	/**
	 * 更新盘点计划
	 * 
	 * @param checkPlan
	 */
	public void updateCheckPlan(CheckPlan checkPlan);
	
	/**
	 * 删除盘点计划
	 * 
	 * @param id
	 * */
	public void deletePlan(Long id);

}
