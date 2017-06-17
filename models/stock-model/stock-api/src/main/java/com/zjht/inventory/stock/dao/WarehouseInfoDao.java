package com.zjht.inventory.stock.dao;


import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import java.util.List;

/**
 * Created by longshuzhen on 2016/10/18.
 */
public interface WarehouseInfoDao {

    /**
     * 按照id查找仓库
     * @Param id
     * @return WarehouseInfo
     */
    WarehouseInfo findWarehouseById(Long id);

    /**
     * 查询所有仓库
     * @return List<WarehouseInfo>
     */
    List<WarehouseInfo> findAll();

    Page<WarehouseInfo> FindList(int index, int size);//查询物料列表

    /**
     * 按条件查询仓库列表
     * @param index
     * @param size
     * @return
     */
    Page<WarehouseInfo> findSomeWarehouse(WarehouseInfo warehouse,int index,int size);
    
    /**
     * 根据管理员查询仓库列表
     * 
     * @param warehouseKepper
     * @return 
     * */
    List<WarehouseInfo> findWarehouseInfoByWarehouseKepper(String warehouseKepper);

    /**
     *查询仓库中当前存在的管理员列表
     */
    List<WarehouseInfo> findWarehouseKeeperList();

    /**
     * 根据名字查询是否存在该仓库
     * @param name
     * @return
     */
    WarehouseInfo findWarehouseByName(String name);

}
