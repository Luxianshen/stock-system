package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.dao.WarehouseInfoWriteDao;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@ExportService(serviceTypes = {WarehouseInfoService.class})
public class WarehouseServiceImpl implements WarehouseInfoService {
    @ImportService
    private WarehouseInfoWriteDao warehouseDao;

    @Override
    @Transactional
    public void updateWarehouse(WarehouseInfo warehouse) {
        warehouseDao.updateWarehouse(warehouse);
    }

    @Override
    @Transactional
    public void createWarehouse(WarehouseInfo warehouse) {
        warehouseDao.createWarehouse(warehouse);
    }

    @Override
    @Transactional
    public void removeWarehouse(WarehouseInfo warehouse) {
        warehouseDao.removeWarehouse(warehouse);
    }
}
