package com.zjht.inventory.stock.dao;

import org.smarabbit.massy.annotation.ExportService;

import com.zjht.inventory.stock.dao.mapper.CheckResultMapper;
import com.zjht.inventory.stock.entity.CheckResult;

@ExportService(serviceTypes = { CheckResultDao.class, CheckResultWriteDao.class })
public class CheckResultDaoImpl implements CheckResultDao {

	private CheckResultMapper checkResultMapper;

	public void setCheckResultMapper(CheckResultMapper checkResultMapper) {
		this.checkResultMapper = checkResultMapper;
	}

	@Override
	public CheckResult findCheckResultByPlanId(String planId) {
		return checkResultMapper.findCheckResultByPlanId(planId);
	}

}
