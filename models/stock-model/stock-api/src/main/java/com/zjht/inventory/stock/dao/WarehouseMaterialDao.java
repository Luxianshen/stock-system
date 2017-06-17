package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.InventoryDetailed;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import com.zjht.inventory.stock.entity.WarehouseMaterial;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longshuzhen on 2016/10/20。
 */
public interface WarehouseMaterialDao {

    /**
     * 查询所有仓库的所有物料
     * @param index  下标
     * @param size   每页条数
     * @return。
     */
    Page<Map> findAllMaterial(int index, int size);

    /**
     * 根据仓库名查询仓库里的物料
     * @param warehouseName   仓库名称
     * @param index           下标
     * @param size            每页条数
     * @return。
     */
    Page<Map> findSomeMaterialByWareshoueName(String warehouseName, int index, int size);

    List<WarehouseMaterial> findMetarialByWarehouseId(Long warehouseId);

    /**
     * 根据仓库ID查询仓库物料记录
     * @param warehouseId  仓库ID
     * @return。
     */
    List<WarehouseMaterial> findWarehouseMaterialByWarehouseId(Long warehouseId);

    /**
     * 根据仓库ID列表查询仓库物料记录
     * @param warehouseInfoList  仓库列表
     * @return。
     */
    List<WarehouseMaterial> findWarehouseMaterialByWarehouseIdList(
            List<WarehouseInfo> warehouseInfoList);

    /**
     * 根据仓库ID与物料ID查找仓库物料记录
     * @param warehouseId  仓库ID
     * @param materielList   物料列表
     * @return。
     */
    List<WarehouseMaterial> findByWarehouseIdAndIdList(
            Long warehouseId, ArrayList<String> materielList);

    /**
     * 根据仓库ID与物料ID查找仓库物料记录
     * @param warehouseId   仓库ID
     * @param materialId    物料ID
     * @return。
     */
    WarehouseMaterial findByWarehouseIdAndMaterialId(Long warehouseId, Long materialId);

    /**
     * 根据管理员进行统计仓库物料
     * @param warehouseInfoList  仓库列表
     * @return。
     */
    Page<Map> findAllMaterialByWarehouseKeeper(
            List<WarehouseInfo> warehouseInfoList, int index, int size);

    /**
     * 按物料属性统计  获取属性数据
     * @param warehouseId   仓库id
     * @param metadatas     物料id
     * @param tableName    表名
     * @param index         下标
     * @param size          每页条数
     * @return。
     */
    Page<LinkedHashMap<String, Object>> countByMaterialIdAndWarehouseId(
            Long warehouseId, ArrayList<Metadata> metadatas, String tableName, int index, int size);

    Page<LinkedHashMap<String, Object>> countByMaterialBySomeWarehouseKeeper(
            List<Long> inventoryDetailedId, ArrayList<Metadata> metadatas,
            String tableName, int index, int size);

    //运营终端查询库存数量（根据）
    List<Map<String,Long>> countForPropertiesForInterface(
            List<InventoryDetailed> inventoryDetailedId, String tableName,
            LinkedHashMap<String, String> mapList);

    WarehouseMaterial findById(Long warehouseMaterialId);

    //获取当前表的字段名  用以匹配输入的参数是否有错
    List<LinkedHashMap<String, Object>> getColumnNameForInterface(String tableName);
}
