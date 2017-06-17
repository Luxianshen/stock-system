package com.zjht.inventory.test.service;

import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.inventory.test.dao.TestWriteDao;
import com.zjht.inventory.test.entity.Test;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
@ExportService(serviceTypes = {TestService.class})
public class TestServiceImpl implements TestService {
    @ImportService
    private TestWriteDao testDao;

    @Override
    @Transactional
    public void createTest(Test test) {
        testDao.save(test);
    }
}
