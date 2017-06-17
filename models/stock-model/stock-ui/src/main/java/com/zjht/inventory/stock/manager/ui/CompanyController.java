package com.zjht.inventory.stock.manager.ui;

import com.zjht.inventory.stock.dao.CompanyDao;
import com.zjht.inventory.stock.entity.Company;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
@Controller
public class CompanyController {
    @ImportService
    CompanyDao companyDao;

    @RequestMapping(value = "/company/getCompany.html", method = RequestMethod.POST)
    @ResponseBody
    public List<Company> getCompany(){
        List<Company> data = companyDao.getCompanyList();
        return data;
    }

}
