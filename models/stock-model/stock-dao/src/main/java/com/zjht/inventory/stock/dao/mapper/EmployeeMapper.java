package com.zjht.inventory.stock.dao.mapper;

import com.zjht.inventory.stock.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public interface EmployeeMapper {
    List<Employee> getEmployeeList(@Param("companyCode") String companyCode, @Param("departmentCode") String departmentCode);
    Employee getEmployeeId(@Param("employeeName") String employeeName);

}
