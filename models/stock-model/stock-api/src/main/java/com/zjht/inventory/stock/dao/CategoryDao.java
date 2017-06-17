package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lujiasheng on 2016/10/28。
 */
public interface CategoryDao {

    List<Category> findTreeData();

    /**
     * 根据上级编号查找子级。
     *
     * @param superiorId 父类ID
     * @return 分类列表
     */
    ArrayList<Category> findBySuperiorId(Long superiorId);

    /**
     * 根据条件查询种类。
     *
     * @param params 参数
     * @return 分类列表
     */
    ArrayList<Category> findByParams(Map<String, Object> params);

    /**
     * 根据仓库ID与上级ID查询种类。
     *
     * @param params 参数
     * @return 一个分类
     */
    Category findByWarehouseId(Map<String, Object> params);

    Category findById(Long id);
}
