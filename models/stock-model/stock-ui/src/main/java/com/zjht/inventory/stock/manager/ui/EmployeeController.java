package com.zjht.inventory.stock.manager.ui;

import com.zjht.inventory.stock.dao.EmployeeDao;
import com.zjht.inventory.stock.entity.Employee;
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
public class EmployeeController {
    @ImportService
    EmployeeDao employeeDao;

    @RequestMapping(value = "/employee/getEmployee.html", method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> getEmployee(@RequestParam(value = "companyCode") String companyCode,@RequestParam(value = "departmentCode") String departmentCode){
        List<Employee> data = employeeDao.getEmployeeList(companyCode,departmentCode);
        return data;
    }
}
