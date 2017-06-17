package com.zjht.inventory.stock.dao.mapper;


import com.zjht.inventory.stock.entity.Metadata;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by lujiasheng on 2016/10/18.
 */

public interface MetadataMapper {


    ArrayList<Metadata> findMetadataByMaterialId(@Param("id") Long id);

    ArrayList<LinkedHashMap<Long,Object>> findInventoryDetailBy(
            @Param("id") Long id,
            @Param("table_name") String table_name);

    ArrayList<LinkedHashMap<Long,Object>> FindDataBySql(@Param("sql") String sql);
}
