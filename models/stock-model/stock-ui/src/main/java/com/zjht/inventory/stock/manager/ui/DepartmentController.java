package com.zjht.inventory.stock.manager.ui;

import com.zjht.inventory.stock.dao.DepartmentDao;
import com.zjht.inventory.stock.entity.Department;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
@Controller
public class DepartmentController {
    @ImportService
    DepartmentDao departmentDao;

    @RequestMapping(value = "/department/getDepartment.html", method = RequestMethod.POST)
    @ResponseBody
    public List<Department> getDepartment(@RequestParam(value = "companyCode") String companyCode){
        List<Department> data = departmentDao.getDepartmentList(companyCode);
        return data;
    }
}
