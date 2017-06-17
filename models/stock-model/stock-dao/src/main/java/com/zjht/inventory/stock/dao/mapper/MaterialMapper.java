package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lujiasheng on 2016/10/18。
 */

public interface MaterialMapper {

    Page<Materiel> findList();// mapper定义抽象接口

    Materiel findById(@Param("id") Long id);

    List<Metadata> findMeteDataName(@Param("id") Long id);

    List<LinkedHashMap<String, Object>> findData();

    List<Materiel> findByCategoryId(@Param("id") Long id);

    /**
     * 根据仓库ID查找物料记录。
     *
     * @param warehouseId 仓库ID
     * @return 列表
     */
    List<Materiel> fineMaterialByWarehouseId(@Param("warehouseId") Long warehouseId);

    List<Materiel> findBySearchCode(@Param("searchcode") String searchcode);

    void addNewMaterial(@Param("material") Materiel material);

    List<Materiel> findByCategory();

    void addNewMetadata(@Param("list") Metadata metadataList);

    void addNewMetadataNoDictionary(@Param("list") Metadata metadata);

    Long getAddId();

    void createTable(@Param("sql") String sql);

    List<Metadata> findMeteData(@Param("id") Long materialId);

    void updateOldMetadata(@Param("metadata") Metadata metadata);

    void updateTable(@Param("sql") String sql);

    Page<Materiel> searchList(@Param("material") Materiel material);

    List<Materiel> findMaterielCode();

    ArrayList<Materiel> findMaterialList();// 查询物料列表

    Materiel findByTable(@Param("table") String table);

    /**
     * 根据仓库ID与类型ID查询物料。
     *
     * @param params 参数
     */
    List<Materiel> findByParams(Map<String, Object> params);

    //用以查询物料列表接口调用
    List<Materiel> findByCategoryIdForInterface(@Param("id") Long id);

    /**
     * 根据表名判断该表是否存在数据库中。
     *
     * @param tableName 表名
     * @return 数据
     */
    List<Map<String, String>> getTableInfo(@Param("tableName") String tableName);

    /**
     * 根据物料ID列表查询物料。
     *
     * @param materielList 物料列表
     */
    List<Materiel> findByMaterielIds(@Param("materielList") ArrayList<String> materielList);

    Materiel findMaterialByCode(@Param("code") String code);

}
