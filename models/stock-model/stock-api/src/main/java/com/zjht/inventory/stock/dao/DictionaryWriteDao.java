package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Dictionary;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/31。
 */
public interface DictionaryWriteDao extends DictionaryDao {

    void updateDictionaryName(Long id, String name);

    void updateDictionaryOldData(List<Dictionary> updatedata);

    void updateDictionaryNewData(List<Dictionary> adddata);

    void addNewDictionary(String name, String code);
}
