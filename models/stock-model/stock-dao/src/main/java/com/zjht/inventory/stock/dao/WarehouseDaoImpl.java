package com.zjht.inventory.stock.dao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.WarehouseMapper;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/18.
 */

@ExportService(serviceTypes = {WarehouseInfoDao.class,WarehouseInfoWriteDao.class})
public class WarehouseDaoImpl implements WarehouseInfoWriteDao{

    private WarehouseMapper warehouseMapper;

    public void setWarehouseMapper(WarehouseMapper warehouseMapper) {
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public WarehouseInfo findWarehouseById(Long id) {
        return warehouseMapper.findWarehouseById(id);
    }

    @Override
    public List<WarehouseInfo> findAll() {
        return warehouseMapper.findAll();
    }

    @Override
    public Page<WarehouseInfo> FindList(int index, int size) {
        PageHelper.startPage(index,size);
        return warehouseMapper.FindList();
    }

    @Override
    public Page<WarehouseInfo> findSomeWarehouse(WarehouseInfo warehouse,int index, int size) {
        PageHelper.startPage(index,size);
        return warehouseMapper.findSomeWarehouse(warehouse);
    }


    @Override
    public int updateWarehouse(WarehouseInfo warehouse) {
        return warehouseMapper.updateWarehouse(warehouse);
    }

    @Override
    public int createWarehouse(WarehouseInfo warehouse) {
         return warehouseMapper.saveWarehouse(warehouse);
    }

    @Override
    public int removeWarehouse(WarehouseInfo warehouse) { return warehouseMapper.removeWarehouse(warehouse);}

    @Override
	public List<WarehouseInfo> findWarehouseInfoByWarehouseKepper(String warehouseKepper) {
		return warehouseMapper.findWarehouseInfoByWarehouseKepper(warehouseKepper);
	}

    @Override
    public List<WarehouseInfo> findWarehouseKeeperList() {
        return warehouseMapper.findWarehouseKeeperList();
    }

    @Override
    public WarehouseInfo findWarehouseByName(String name) {
        return warehouseMapper.findWarehouseByName(name);
    }
}
