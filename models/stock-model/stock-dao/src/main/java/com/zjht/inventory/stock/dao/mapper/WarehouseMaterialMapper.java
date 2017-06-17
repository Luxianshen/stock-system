package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.InventoryDetailed;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import com.zjht.inventory.stock.entity.WarehouseMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface WarehouseMaterialMapper {

    /**
     * 根据仓库ID查询仓库物料记录
     * @param warehouseId  仓库ID
     * @return。
     */
    List<WarehouseMaterial> findWarehouseMaterialByWarehouseId(
            @Param("warehouseId") Long warehouseId);

    /**
     * 根据仓库ID与物料ID查找仓库物料记录
     *
     * @param warehouseId  仓库ID
     * @param materialId   物料ID
     * @return。
     */
    WarehouseMaterial findByWarehouseIdAndMaterialId(
            @Param("warehouseId") Long warehouseId, @Param("materialId") Long materialId);

    /**
     * 根据仓库ID与物料ID查找仓库物料记录
     *
     * @param warehouseId  仓库ID
     * @param materielList 物料列表
     * @return。
     */
    List<WarehouseMaterial> findByWarehouseIdAndIdList(
            @Param("warehouseId") Long warehouseId,
            @Param("materielList") ArrayList<String> materielList);

    Page<Map> findAllMaterial();

    Page<Map> findAllMaterialByWarehouseKeeper(
            @Param("warehouseInfoList") List<WarehouseInfo> warehouseInfoList);

    Page<Map> findSomeMaterialByWareshoueName(@Param("warehouseName") String warehouseName);


    //按物料属性统计  获取属性数据
    Page<LinkedHashMap<String, Object>> countByMaterialIdAndWarehouseId(
            @Param("warehouseId") Long warehouseId,
            @Param("metadatas") ArrayList<Metadata> metadatas,
            @Param("tableName") String tableName
    );

    //按物料属性统计  获取属性数据
    Page<LinkedHashMap<String, Object>> countByMaterialBySomeWarehouseKeeper(
            @Param("inventoryDetailedId") List<Long> inventoryDetailedId,
            @Param("metadatas") ArrayList<Metadata> metadatas,
            @Param("tableName") String tableName
    );

    List<WarehouseMaterial> findWarehouseMaterialByWarehouseIdList(
            @Param("warehouseMaterialIdList")
                    List<WarehouseInfo> warehouseMaterialIdList);

    //运营终端查询库存数量（根据）
    List<Map<String,Long>> countForPropertiesForInterface(
            @Param("inventoryDetailedId") List<InventoryDetailed> inventoryDetailedId,
            @Param("tableName") String tableName, @Param("mapList") LinkedHashMap<String, String> mapList);

    void updateCount(@Param("warehouseMaterialId") Long warehouseMaterialId,
                     @Param("countNum") Long countNum);

    void insertOne(@Param("id") Long id,
                   @Param("warehouseId") Long warehouseId,
                   @Param("materielId") Long materielId,
                   @Param("remark") String remark);

    WarehouseMaterial findById(
            @Param("warehouseMaterialId") Long warehouseMaterialId);

    //获取当前表的字段名  用以匹配输入的参数是否有错
    List<LinkedHashMap<String, Object>> getColumnNameForInterface(
            @Param("tableName") String tableName);
    
}
