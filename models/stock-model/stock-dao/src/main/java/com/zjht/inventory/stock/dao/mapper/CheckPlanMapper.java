package com.zjht.inventory.stock.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.CheckPlan;
import com.zjht.inventory.stock.entity.CheckPlanDto;
import com.zjht.inventory.stock.entity.PlanDifferDto;
import com.zjht.inventory.stock.entity.PlanRecordDto;

public interface CheckPlanMapper {

	/**
	 * 创建盘点计划
	 * 
	 * @param checkPlan
	 */
	public void save(@Param("checkPlan") CheckPlan checkPlan);

	/**
	 * 删除盘点记录
	 * 
	 * @param id
	 */
	public void deletePlan(@Param("id") Long id);

	/**
	 * 查询盘点计划
	 * 
	 * @param checkPlan
	 * @return
	 */
	public Page<CheckPlanDto> findCheckPlanList(@Param("checkPlanDto") CheckPlanDto checkPlanDto);

	/**
	 * 根据ID查找盘点计划记录
	 * 
	 * @param id
	 * 
	 */
	public CheckPlan findCheckPlanByID(@Param("id") String id);

	/**
	 * 更新盘点计划
	 * 
	 * @param checkPlan
	 */
	public void updateCheckPlan(@Param("checkPlan") CheckPlan checkPlan);

	/**
	 * 根据盘点计划名称和资产编号列表查询存在的盘点记录
	 * 
	 * @param checkPlanId
	 * @param assetNo
	 * @return
	 * 
	 */
	public List<String> findExistAssetNo(@Param("checkPlanId") String checkPlanId,
			@Param("assetList") List<String> assetList);

	/**
	 * 查询盘点计划
	 * 
	 * @param checkPlan
	 * @return
	 */
	public Page<PlanRecordDto> findPlanRecordList(Map<String, Object> map);

	/**
	 * 根据存在的资产编号与盘点计划编号查询所有明细
	 * 
	 * @param checkPlanId
	 * @param existList
	 * @return
	 */
	public PlanDifferDto findExistDetail(@Param("checkPlanId") String checkPlanId,
			@Param("existList") List<String> existList);
}
