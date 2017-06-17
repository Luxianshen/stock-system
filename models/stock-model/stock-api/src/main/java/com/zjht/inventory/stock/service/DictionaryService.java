package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.Dictionary;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/31。
 */
public interface DictionaryService {

    void updateDictionary(Dictionary dictionaryCode, List<Dictionary> dictionaries);

    void addDictionary(Dictionary dictionaryCode, List<Dictionary> dictionaries);

    void addDictionary(Dictionary dictionaryCode);

    void changeName(Long id, String name);
}
