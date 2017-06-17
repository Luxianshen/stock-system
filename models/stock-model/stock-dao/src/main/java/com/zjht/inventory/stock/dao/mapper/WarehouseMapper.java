package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by longshuzhen on 2016/10/18.
 */
public interface WarehouseMapper {

    /**
     * 通过id查询仓库
     * @param id
     * @return WarehouseInfo
     */
    WarehouseInfo findWarehouseById(@Param("id") Long id);

    /**
     * 查询所有仓库
     * @return List
     */
    List<WarehouseInfo> findAll();

    /**
     * 查询所有仓库 分页显示
     * @return
     */
    Page<WarehouseInfo> FindList();//mapper定义抽象接口

    /**
     * 按条件查询仓库 分页显示
     * @param warehouse
     * @return
     */
    Page<WarehouseInfo> findSomeWarehouse(@Param("warehouse") WarehouseInfo warehouse);

    //更新仓库信息
    int updateWarehouse(@Param("warehouse") WarehouseInfo warehouse);

    //新增仓库
    int saveWarehouse(@Param("warehouse") WarehouseInfo warehouse);

    //逻辑删除仓库
    int removeWarehouse(@Param("warehouse") WarehouseInfo warehouse);
    
    /**
     * 根据管理员查询仓库列表
     * 
     * @param warehouseKepper
     * @return 
     * */
    List<WarehouseInfo> findWarehouseInfoByWarehouseKepper(@Param("warehouseKepper") String warehouseKepper);

    /**
     * 查询仓库中当前存在的管理员列表
     * @return
     */
    List<WarehouseInfo> findWarehouseKeeperList();

    WarehouseInfo findWarehouseByName(@Param("name") String name);

}
