package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 元数据DAO。
 */
public interface MetadataDao {

    ArrayList<Metadata> findMetadataByMaterialId(Long tableName);

   List<LinkedHashMap<Long, Object>> findInventoryDetailBy(Long id, String tableName);
    /*Page<LinkedHashMap<String, Object>> findInventoryDetailByPage(
            Long id, String tableName, int index, int size);



    ArrayList<LinkedHashMap<String, Object>> findDataBySql(String sql);

    Page<LinkedHashMap<String, Object>> findDataBySql(String sql, int index, int size);*/

    //查找参与统计的字段
    //ArrayList<Metadata> findMetadataByStatistics();

    //根据元数据ID查找元数据
    //Metadata findMetadataById(Long id);

    //用于数据推送   根据物料ID和元数据code唯一确定一条元数据
    //Metadata findMetadataByMaterialIdAndCode(Long materialId, String code);

    //唯一检查
    //List<LinkedHashMap<String, String>> only(String table, String code, String content);
}
