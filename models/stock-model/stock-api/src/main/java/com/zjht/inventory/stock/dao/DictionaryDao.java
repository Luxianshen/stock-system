package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Dictionary;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/31。
 */
public interface DictionaryDao {

    List<Dictionary> findDataByCode(String code);

    List<Dictionary> findDictionaryCode();

    Page<Dictionary> findAllDictionary(int index, int size);

    Dictionary findDictionaryByCode(String dictionaryCode);

    Page<Dictionary> findAllDictionaryItem(int index, int size, String dictionaryCode);

    //用于生产推送数据
    Dictionary findDictionaryItemById(Long id);

    Dictionary findDictionItemByCode(String code, String optionName);
}
