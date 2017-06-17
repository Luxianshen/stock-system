package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.CategoryMapper;
import com.zjht.inventory.stock.entity.Category;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lujiasheng on 2016/10/28。
 */
@ExportService(serviceTypes = {CategoryDao.class, CategoryWriteDao.class})
public class CategoryDaoImpl implements CategoryWriteDao {
    @ImportService
    private CategoryMapper categoryMapper;

    /**
     * 查询树的数据。
     *
     * @return 分类列表
     */
    @Override
    public List<Category> findTreeData() {
        return categoryMapper.findTreeData();
    }

    /**
     * 根据父类ID查找某一类的 树节点的数据。
     *
     * @param superiorId 父亲ID
     * @return 分类列表
     */
    @Override
    public ArrayList<Category> findBySuperiorId(Long superiorId) {
        return categoryMapper.findBySuperiorId(superiorId);
    }

    /**
     * 根据ID查找分类。
     *
     * @param id 主键
     * @return 一条分类信息
     */
    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

    /**
     * 根据参数查找。
     *
     * @param params 参数
     * @return 分类列表
     */
    @Override
    public ArrayList<Category> findByParams(Map<String, Object> params) {
        return categoryMapper.findByParams(params);
    }

    /**
     * 仓库ID。
     *
     * @param params 参数
     * @return 仓库ID
     */
    @Override
    public Category findByWarehouseId(Map<String, Object> params) {
        return categoryMapper.findByWarehouseId(params);
    }

    /**
     * 新增分类。
     *
     * @param category 分类
     */
    @Override
    public void addNewCategory(Category category) {
        categoryMapper.addNewCategory(category);
    }

    /**
     * 更新分类。
     *
     * @param category 更新的分类
     */
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    /**
     * 删除分类。
     *
     * @param id 分类主键
     */
    @Override
    public void delCategory(Long id) {
        categoryMapper.delCategory(id);
    }

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
