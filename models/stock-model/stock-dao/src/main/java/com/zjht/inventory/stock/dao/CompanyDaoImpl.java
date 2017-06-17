package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.CompanyMapper;
import com.zjht.inventory.stock.entity.Company;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/27.
 */
@ExportService (serviceTypes = {CompanyDao.class,CompanyWriteDao.class})
public class CompanyDaoImpl implements CompanyWriteDao {
    private CompanyMapper companyMapper;

    public void setCompanyMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public List<Company> getCompanyList() {
        return companyMapper.getCompanyList();
    }
}
