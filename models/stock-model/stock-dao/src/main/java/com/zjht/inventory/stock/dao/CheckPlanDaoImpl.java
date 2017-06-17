package com.zjht.inventory.stock.dao;

import java.util.List;
import java.util.Map;

import org.smarabbit.massy.annotation.ExportService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.CheckPlanMapper;
import com.zjht.inventory.stock.entity.CheckPlan;
import com.zjht.inventory.stock.entity.CheckPlanDto;
import com.zjht.inventory.stock.entity.PlanDifferDto;
import com.zjht.inventory.stock.entity.PlanRecordDto;

@ExportService(serviceTypes = { CheckPlanDao.class, CheckPlanWriteDao.class })
public class CheckPlanDaoImpl implements CheckPlanWriteDao {

	private CheckPlanMapper checkPlanMapper;

	public void setCheckPlanMapper(CheckPlanMapper checkPlanMapper) {
		this.checkPlanMapper = checkPlanMapper;
	}

	@Override
	public void save(CheckPlan checkPlan) {
		checkPlanMapper.save(checkPlan);
	}

	@Override
	public Page<CheckPlanDto> findCheckPlanList(CheckPlanDto checkPlanDto, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		return checkPlanMapper.findCheckPlanList(checkPlanDto);
	}

	@Override
	public CheckPlan findCheckPlanByID(String id) {
		return checkPlanMapper.findCheckPlanByID(id);
	}

	@Override
	public void updateCheckPlan(CheckPlan checkPlan) {
		checkPlanMapper.updateCheckPlan(checkPlan);
	}

	@Override
	public List<String> findExistAssetNo(String checkPlanId, List<String> assetList) {
		return checkPlanMapper.findExistAssetNo(checkPlanId, assetList);
	}

	@Override
	public void deletePlan(Long id) {
		checkPlanMapper.deletePlan(id);
	}

	@Override
	public Page<PlanRecordDto> findPlanRecordList(Map<String, Object> map, int pageIndex, int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		return checkPlanMapper.findPlanRecordList(map);
	}

	@Override
	public PlanDifferDto findExistDetail(String checkPlanId, List<String> existList) {
		return checkPlanMapper.findExistDetail(checkPlanId, existList);
	}

}
