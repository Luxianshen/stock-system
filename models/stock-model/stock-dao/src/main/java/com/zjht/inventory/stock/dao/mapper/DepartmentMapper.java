package com.zjht.inventory.stock.dao.mapper;

import com.zjht.inventory.stock.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
public interface DepartmentMapper {
    List<Department> getDepartmentList(@Param("companyCode") String companyCode);
}
