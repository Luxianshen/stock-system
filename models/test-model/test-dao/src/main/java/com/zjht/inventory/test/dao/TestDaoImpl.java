package com.zjht.inventory.test.dao;

import com.zjht.inventory.test.dao.mapper.TestMapper;
import com.zjht.inventory.test.dao.TestDao;
import com.zjht.inventory.test.dao.TestWriteDao;
import com.zjht.inventory.test.entity.Test;
import org.smarabbit.massy.annotation.ExportService;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
@ExportService(serviceTypes = {TestDao.class, TestWriteDao.class})
public class TestDaoImpl implements TestWriteDao {
    private TestMapper testMapper;

    @Override
    public int save(Test test) {
        return testMapper.save(test);
    }

    @Override
    public Test findById(Long id) {
        return testMapper.findById(id);
    }

    public void setTestMapper(TestMapper testMapper) {
        this.testMapper = testMapper;
    }


}
