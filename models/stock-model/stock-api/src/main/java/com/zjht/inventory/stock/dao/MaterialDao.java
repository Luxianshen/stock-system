package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lujiasheng on 2016/10/18。
 */
public interface MaterialDao {

    Page<Materiel> findList(int index, int size);// 查询物料列表

    Page<Materiel> searchList(int index, int size, Materiel material);

    Materiel findById(Long id);// 根据传进来的ID查找一条数据

    List<Metadata> findMeteDataName(Long materielCategoryId);

    ArrayList<Materiel> findMaterialList();// 查询物料列表

    /**
     * 根据仓库ID查找物料记录。
     *
     * @param warehouseId 仓库id
     * @return null
     */
    List<Materiel> fineMaterialByWarehouseId(Long warehouseId);

    List<Materiel> findByCategoryId(Long id);

    List<Materiel> findBySearchCode(String searchcode);

    List<Materiel> findByCategory();

    Long getAddId();

    List<Metadata> findMeteData(Long materialId);

    List<Materiel> findMaterielCode();

    Materiel findByTable(String table);

    /**
     * 根据仓库ID与类型ID查询物料。
     *
     * @param params 参数
     */
    List<Materiel> findByParams(Map<String, Object> params);

    /**
     * 根据物料ID列表查询物料。
     *
     * @param materielList 物料ID列表
     */
    List<Materiel> findByMaterielIds(ArrayList<String> materielList);

    //用以查询物料列表接口调用
    List<Materiel> findByCategoryIdForInterface(Long id);

    /**
     * 根据表名判断该表是否存在数据库中。
     *
     * @param tableName 表名
     * @return 数据
     */
    List<Map<String, String>> getTableInfo(String tableName);

    //用于推送数据  根据code查找物料
    Materiel findMaterialByCode(String code);

}
