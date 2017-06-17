package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Category;

/**
 * Created by lujiasheng on 2016/10/28。
 */
public interface CategoryWriteDao extends CategoryDao {

    //新增分类
    void addNewCategory(Category category);

    void updateCategory(Category category);

    void delCategory(Long id);
}
