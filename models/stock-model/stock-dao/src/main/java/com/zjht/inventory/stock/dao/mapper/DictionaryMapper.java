package com.zjht.inventory.stock.dao.mapper;

import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18。
 */

public interface DictionaryMapper {

    List<Dictionary> findDataByCode(@Param("code") String code);

    List<Dictionary> findDictionaryCode();

    Page<Dictionary> findAllDictionary();

    Dictionary findDictionaryByCode(@Param("code") String dictionaryCode);

    Page<Dictionary> findAllDictionaryItem(@Param("code") String dictionaryCode);

    void updateDictionaryName(@Param("id") Long id, @Param("name") String name);

    void updateDictionaryOldData(@Param("dictionary") Dictionary dictionary);

    void updateDictionaryNewData(@Param("dictionary") Dictionary dictionary);

    void addDictionaryNewData(@Param("name") String name, @Param("code") String code);

    Dictionary findDictionaryItemById(@Param("id") Long id);

    Dictionary findDictionItemByCode(@Param("code") String code,
                                     @Param("optionName") String optionName);
}
