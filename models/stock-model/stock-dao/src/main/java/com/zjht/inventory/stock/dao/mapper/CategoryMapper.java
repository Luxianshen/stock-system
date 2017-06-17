package com.zjht.inventory.stock.dao.mapper;

import com.zjht.inventory.stock.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 。
 */
public interface CategoryMapper {

    List<Category> findTreeData();

    ArrayList<Category> findBySuperiorId(@Param("superiorId") Long superiorId);

    Category findById(@Param("id") Long id);
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
     * @return 分类
     */
    Category findByWarehouseId(Map<String, Object> params);

    void addNewCategory(@Param("category") Category category);

    void updateCategory(@Param("category") Category category);

    void delCategory(@Param("id") Long id);
}
