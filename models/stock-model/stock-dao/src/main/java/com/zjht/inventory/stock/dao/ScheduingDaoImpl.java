package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.ScheduingMapper;
import com.zjht.inventory.stock.entity.Scheduing;
import com.zjht.inventory.stock.entity.ScheduingDetail;
import org.smarabbit.massy.annotation.ExportService;

/**
 *
 */
@ExportService(serviceTypes = {ScheduingDao.class, ScheduingWriteDao.class})
public class ScheduingDaoImpl implements ScheduingWriteDao {
    public void setScheduingMapper(ScheduingMapper scheduingMapper) {
        this.scheduingMapper = scheduingMapper;
    }
    private ScheduingMapper scheduingMapper;


    @Override
    public Scheduing findById(String id) {
        return scheduingMapper.findById(id);
    }

    @Override
    public Page<Scheduing> findByPage(Long applyId, String orderId, Long inOutType, String operator, String drawingPerson, String startTime, String endTime, int index, int size) {
        PageHelper.startPage(index,size);
        return scheduingMapper.findByPage(applyId, orderId, inOutType, operator, drawingPerson, startTime, endTime);
    }

    @Override
    public ScheduingDetail findDetailBySystrace(String systrace) {
        return scheduingMapper.findDetailBySystrace(systrace);
    }

    @Override
    public Scheduing findBySystrace(String systrace) {
        return scheduingMapper.findBySystrace(systrace);
    }
}
