package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Department;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public interface DepartmentDao {
    List<Department> getDepartmentList(String companyCode);
}
