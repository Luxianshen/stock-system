package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.ManagerMapper;
import com.zjht.inventory.stock.entity.Manager;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/20.
 */
@ExportService(serviceTypes = {ManagerDao.class})
public class ManagerDaoImpl implements ManagerDao{
    private ManagerMapper managerMapper;

    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public List<Manager> findAllManager() {
        return managerMapper.findAllManager();
    }
}
