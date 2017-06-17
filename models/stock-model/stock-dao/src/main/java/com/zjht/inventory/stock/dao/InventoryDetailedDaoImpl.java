package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.InventoryDetailedMapper;
import com.zjht.inventory.stock.entity.InventoryDetailed;
import org.smarabbit.massy.annotation.ExportService;
/**
 * Created by lujiasheng on 2016/10/21.
 */
@ExportService(serviceTypes = {InventoryDetailedDao.class,InventoryDetailedWriteDao.class})
public class InventoryDetailedDaoImpl implements InventoryDetailedWriteDao {

    private InventoryDetailedMapper inventoryDetailedMapper;

    public InventoryDetailedMapper getInventoryDetailedMapper() {
        return inventoryDetailedMapper;
    }

    public void setInventoryDetailedMapper(InventoryDetailedMapper inventoryDetailedMapper) {
        this.inventoryDetailedMapper = inventoryDetailedMapper;
    }

    @Override
    public InventoryDetailed findById(Long id, String table) {
        return inventoryDetailedMapper.findById(id,table);
    }

    @Override
    public void changeStatusById(Long id, long status) {
        inventoryDetailedMapper.changeStatusById(id,status);
    }
}
