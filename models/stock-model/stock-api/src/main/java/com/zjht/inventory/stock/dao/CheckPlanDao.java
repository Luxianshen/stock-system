package com.zjht.inventory.stock.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.CheckPlan;
import com.zjht.inventory.stock.entity.CheckPlanDto;
import com.zjht.inventory.stock.entity.PlanDifferDto;
import com.zjht.inventory.stock.entity.PlanRecordDto;

public interface CheckPlanDao {

	/**
	 * 查询盘点计划
	 * 
	 * @param checkPlan
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<CheckPlanDto> findCheckPlanList(CheckPlanDto checkPlanDto, int pageIndex, int pageSize);

	/**
	 * 根据ID查询盘点计划
	 * 
	 * @param id
	 * @return
	 */
	public CheckPlan findCheckPlanByID(String id);

	/**
	 * 根据盘点计划名称和资产编号列表查询存在的盘点记录
	 * 
	 * @param checkPlanId
	 * @param assetNo
	 * @return
	 * 
	 */
	public List<String> findExistAssetNo(String checkPlanId, List<String> assetList);

	/**
	 * 查询盘点记录
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<PlanRecordDto> findPlanRecordList(Map<String, Object> map, int pageIndex, int pageSize);

	/**
	 * 根据存在的资产编号与盘点计划编号查询所有明细
	 * 
	 * @param checkPlanId
	 * @param existList
	 * @return
	 */
	public PlanDifferDto findExistDetail(String checkPlanId, List<String> existList);
}
