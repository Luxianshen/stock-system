package com.zjht.inventory.stock.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zjht.inventory.stock.entity.CheckResult;

public interface CheckResultMapper {

	/**
	 * 根据盘点计划ID查询盘点结果
	 * 
	 * @param planId
	 * @return
	 */
	public CheckResult findCheckResultByPlanId(@Param("planId") String planId);

}
