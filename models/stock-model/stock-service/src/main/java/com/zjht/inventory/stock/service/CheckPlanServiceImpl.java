package com.zjht.inventory.stock.service;

import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.inventory.stock.dao.CheckPlanWriteDao;
import com.zjht.inventory.stock.entity.CheckPlan;

@ExportService(serviceTypes = { CheckPlanService.class })
public class CheckPlanServiceImpl implements CheckPlanService {

	@ImportService
	private CheckPlanWriteDao checkPlanWriteDao;

	@Override
	@Transactional
	public void createCheckPlan(CheckPlan checkPlan) {
		checkPlanWriteDao.save(checkPlan);
	}

	@Override
	@Transactional
	public void updateCheckPlan(CheckPlan checkPlan) {
		checkPlanWriteDao.updateCheckPlan(checkPlan);
	}

	@Override
	@Transactional
	public void deletePlan(Long id) {
		checkPlanWriteDao.deletePlan(id);
	}

}
