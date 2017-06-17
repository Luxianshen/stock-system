package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.MetadataMapper;
import com.zjht.inventory.stock.entity.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ExportService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lujiasheng on 2016/10/21.
 */
@ExportService(serviceTypes = {MetadataDao.class, MetadataWriteDao.class})
public class MetadataDaoImpl implements MetadataDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private MetadataMapper metadataMapper;

    @Override
    public ArrayList<Metadata> findMetadataByMaterialId(Long id) {
        ArrayList<Metadata> datas = metadataMapper.findMetadataByMaterialId(id);
        return datas != null ? datas : new ArrayList<>();
    }

    @Override
    public List<LinkedHashMap<Long, Object>> findInventoryDetailBy(Long id, String table_name) {
        ArrayList<LinkedHashMap<Long, Object>> maps = metadataMapper.findInventoryDetailBy(id,table_name);
        return maps != null ? maps : new ArrayList<>();
    }
    /*
    @Override
    public List<LinkedHashMap<Long, Object>> findDataBySql(String sql) {
        logger.info(sql);
        ArrayList<LinkedHashMap<Long, Object>> maps = metadataMapper.FindDataBySql(sql);
        return maps!=null?maps:new ArrayList<>();
    }*/

    public void setMetadataMapper(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }
}
