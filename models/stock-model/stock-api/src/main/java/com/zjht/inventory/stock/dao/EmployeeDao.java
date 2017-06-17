package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Employee;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public interface EmployeeDao {
    List<Employee> getEmployeeList(String companyCode, String departmentCode);

    Employee getEmployeeId(String employeeName);
}
