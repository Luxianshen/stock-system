package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.EmployeeMapper;
import com.zjht.inventory.stock.entity.Employee;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
@ExportService(serviceTypes = {EmployeeDao.class,EmployeeWriteDao.class})
public class EmployeeDaoImpl implements EmployeeWriteDao {
    private EmployeeMapper employeeMapper;

    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> getEmployeeList(String companyCode, String departmentCode) {
        return employeeMapper.getEmployeeList(companyCode,departmentCode);
    }

    @Override
    public Employee getEmployeeId(String employeeName) {
        return employeeMapper.getEmployeeId(employeeName);
    }
}
