package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.DepartmentMapper;
import com.zjht.inventory.stock.entity.Department;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
@ExportService (serviceTypes = {DepartmentDao.class,DepartmentWriteDao.class})
public class DepartmentDaoImpl implements DepartmentDao {
    private DepartmentMapper departmentMapper;

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getDepartmentList(String companyCode) {
        return departmentMapper.getDepartmentList(companyCode);
    }
}
