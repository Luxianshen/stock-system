package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.WarehouseMaterialMapper;
import com.zjht.inventory.stock.entity.InventoryDetailed;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import com.zjht.inventory.stock.entity.WarehouseMaterial;
import org.smarabbit.massy.annotation.ExportService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longshuzhen on 2016/10/20。
 */
@ExportService(serviceTypes = {WarehouseMaterialDao.class,WarehouseMaterialWriteDao.class})
public class WarehouseMaterialDaoImpl implements WarehouseMaterialWriteDao {

    private WarehouseMaterialMapper warehouseMaterialMapper;

    public void setWarehouseMaterialMapper(WarehouseMaterialMapper warehouseMaterialMapper) {
        this.warehouseMaterialMapper = warehouseMaterialMapper;
    }

    @Override
    public Page<Map> findAllMaterial(int index, int size) {
        PageHelper.startPage(index,size);
        return warehouseMaterialMapper.findAllMaterial();
    }

    @Override
    public Page<Map> findSomeMaterialByWareshoueName(String warehouseName, int index, int size) {
        PageHelper.startPage(index,size);
        return warehouseMaterialMapper.findSomeMaterialByWareshoueName(warehouseName);
    }

    @Override
    public List<WarehouseMaterial> findMetarialByWarehouseId(Long warehouseId) {
        return warehouseMaterialMapper.findWarehouseMaterialByWarehouseId(warehouseId);
    }

    @Override
    public List<WarehouseMaterial> findWarehouseMaterialByWarehouseId(Long warehouseId) {
        return warehouseMaterialMapper.findWarehouseMaterialByWarehouseId(warehouseId);
    }

    @Override
    public List<WarehouseMaterial> findWarehouseMaterialByWarehouseIdList(
        List<WarehouseInfo> warehouseInfoList) {
        return warehouseMaterialMapper.findWarehouseMaterialByWarehouseIdList(warehouseInfoList);
    }

    @Override
    public WarehouseMaterial findByWarehouseIdAndMaterialId(Long warehouseId, Long materialId) {
        return warehouseMaterialMapper.findByWarehouseIdAndMaterialId(
            warehouseId, materialId);
    }

    @Override
    public Page<Map> findAllMaterialByWarehouseKeeper(
            List<WarehouseInfo> warehouseInfoList, int index, int size) {
        PageHelper.startPage(index,size);
        return warehouseMaterialMapper.findAllMaterialByWarehouseKeeper(warehouseInfoList);
    }

    //按物料属性统计  获取属性数据
    @Override
    public Page<LinkedHashMap<String, Object>> countByMaterialIdAndWarehouseId(
            Long warehouseId, ArrayList<Metadata> metadatas, String tableName, int index, int size) {
        PageHelper.startPage(index,size);
        Page<LinkedHashMap<String, Object>> maps =
            warehouseMaterialMapper.countByMaterialIdAndWarehouseId(
                warehouseId,metadatas,tableName);
        return maps != null ? maps : new Page<>();
    }

    @Override
    public Page<LinkedHashMap<String, Object>> countByMaterialBySomeWarehouseKeeper(
        List<Long> inventoryDetailedId, ArrayList<Metadata> metadatas,
        String tableName, int index, int size) {
        PageHelper.startPage(index,size);
        Page<LinkedHashMap<String, Object>> maps =
            warehouseMaterialMapper.countByMaterialBySomeWarehouseKeeper(
                inventoryDetailedId,metadatas,tableName);
        return maps != null ? maps : new Page<>();
    }

    //运营终端查询库存数量（根据）
    @Override
    public List<Map<String,Long>> countForPropertiesForInterface(
            List<InventoryDetailed> inventoryDetailedId, String tableName,
            LinkedHashMap<String,String> mapList) {
        return warehouseMaterialMapper.countForPropertiesForInterface(
            inventoryDetailedId,tableName,mapList);
    }

    @Override
    public WarehouseMaterial findById(Long warehouseMaterialId) {
        return warehouseMaterialMapper.findById(warehouseMaterialId);
    }

    @Override
    public void updateCount(Long warehouseMaterialId, Long countNum) {
        warehouseMaterialMapper.updateCount(warehouseMaterialId, countNum);
    }

    @Override
    public void insertOne(Long id, Long warehouseId, Long materielId, String remark) {
        warehouseMaterialMapper.insertOne(id, warehouseId, materielId, remark);
    }

    @Override
    public List<WarehouseMaterial> findByWarehouseIdAndIdList(
        Long warehouseId, ArrayList<String> materielList) {
        return warehouseMaterialMapper.findByWarehouseIdAndIdList(
            warehouseId,materielList);
    }

    //获取当前表的字段名  用以匹配输入的参数是否有错
    public List<LinkedHashMap<String, Object>> getColumnNameForInterface(String tableName) {
        return  warehouseMaterialMapper.getColumnNameForInterface(tableName);
    }
}
