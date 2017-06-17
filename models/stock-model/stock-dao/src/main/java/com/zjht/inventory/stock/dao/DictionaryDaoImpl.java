package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.DictionaryMapper;
import com.zjht.inventory.stock.entity.Dictionary;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18。
 */
@ExportService(serviceTypes = {DictionaryDao.class, DictionaryWriteDao.class})
public class DictionaryDaoImpl implements DictionaryWriteDao {

    private DictionaryMapper dictionaryMapper;

    /**
     * 根据code查询字典信息。
     * @param code code
     * @return 字典列表
     */
    @Override
    public List<Dictionary> findDataByCode(String code) {
        return dictionaryMapper.findDataByCode(code);
    }

    /**
     * 查找所有的字典code数据。
     * @return 字典列表
     */
    @Override
    public List<Dictionary> findDictionaryCode() {
        return dictionaryMapper.findDictionaryCode();
    }

    /**
     * 查找所有的字典。
     * @param index 主页数
     * @param size 条数
     * @return 字典列表
     */
    @Override
    public Page<Dictionary> findAllDictionary(int index, int size) {
        PageHelper.startPage(index, size);
        return dictionaryMapper.findAllDictionary();
    }

    /**
     * 根据字典code查找某一本字典。
     * @param dictionaryCode 字典代码
     * @return 字典信息
     */
    @Override
    public Dictionary findDictionaryByCode(String dictionaryCode) {
        return dictionaryMapper.findDictionaryByCode(dictionaryCode);
    }

    /**
     * 查找字典选项。
     * @param index 主页数
     * @param size 条数
     * @param dictionaryCode 字典code
     * @return 字典列表
     */
    @Override
    public Page<Dictionary> findAllDictionaryItem(int index, int size, String dictionaryCode) {
        PageHelper.startPage(index, size);
        return dictionaryMapper.findAllDictionaryItem(dictionaryCode);
    }

    /**
     * 更新字典名字。
     * @param id 主键
     * @param name 字典名
     */
    @Override
    public void updateDictionaryName(Long id, String name) {
        dictionaryMapper.updateDictionaryName(id, name);
    }

    /**
     * 更新字典的选项名和选项值。
     * @param updatedata 更新数据
     */
    @Override
    public void updateDictionaryOldData(List<Dictionary> updatedata) {
        for (Dictionary dictionary : updatedata) {
            dictionaryMapper.updateDictionaryOldData(dictionary);
        }

    }

    /**
     * 新增旧字典的选项名和选项值。
     * @param adddata 新增数据
     */
    @Override
    public void updateDictionaryNewData(List<Dictionary> adddata) {
        for (Dictionary dictionary : adddata) {
            dictionaryMapper.updateDictionaryNewData(dictionary);
        }
    }

    /**
     * 新增字典。
     * @param name 名字
     * @param code code
     */
    @Override
    public void addNewDictionary(String name, String code) {
        dictionaryMapper.addDictionaryNewData(name, code);
    }

    /**
     * 根据ID查找字典选项。
     * @param id 主键
     * @return 字典
     */
    @Override
    public Dictionary findDictionaryItemById(Long id) {
        return dictionaryMapper.findDictionaryItemById(id);
    }

    /**
     * 根据code和选择名查找数据。
     * @param code code
     * @param optionName 选项名
     * @return 字典
     */
    @Override
    public Dictionary findDictionItemByCode(String code, String optionName) {
        return dictionaryMapper.findDictionItemByCode(code, optionName);
    }

    public void setDictionaryMapper(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }
}
