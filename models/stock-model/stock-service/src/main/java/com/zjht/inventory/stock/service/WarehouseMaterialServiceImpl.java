package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.dao.WarehouseMaterialWriteDao;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;

/**
 * Created by longshuzhen on 2017/1/12.
 */
@ExportService(serviceTypes = {WarehouseMaterialService.class})
public class WarehouseMaterialServiceImpl implements WarehouseMaterialService {

    @ImportService
    private WarehouseMaterialWriteDao warehouseMaterialWriteDao;
    @Override
    public void insertOne(Long id, Long warehouseId, Long materielId, String remark) {
        warehouseMaterialWriteDao.insertOne(id, warehouseId, materielId, remark);
    }
}
