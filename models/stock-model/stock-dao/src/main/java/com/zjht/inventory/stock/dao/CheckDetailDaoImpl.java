package com.zjht.inventory.stock.dao;

import org.smarabbit.massy.annotation.ExportService;

import com.zjht.inventory.stock.dao.mapper.CheckDetailMapper;

@ExportService(serviceTypes = { CheckDetailDao.class, CheckDetailWriteDao.class })
public class CheckDetailDaoImpl implements CheckDetailDao {

	private CheckDetailMapper checkDetailMapper;

	public void setCheckDetailMapper(CheckDetailMapper checkDetailMapper) {
		this.checkDetailMapper = checkDetailMapper;
	}

}
