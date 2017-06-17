package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.dao.DictionaryDao;
import com.zjht.inventory.stock.dao.DictionaryWriteDao;
import com.zjht.inventory.stock.entity.Dictionary;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lujiasheng on 2016/12/8。
 */
@ExportService(serviceTypes = {DictionaryService.class})
public class DictionaryServiceImpl implements DictionaryService{

    @ImportService
    private DictionaryWriteDao dictionaryWriteDao;
    @ImportService
    private DictionaryDao dictionaryDao;


    /**
     * 更新字典。
     * @param dictionaryCode 字典code
     * @param dictionaries 字典列表
     */
    @Override
    @Transactional
    public void updateDictionary(Dictionary dictionaryCode, List<Dictionary> dictionaries) {

        //修改第一个表的数据
        dictionaryWriteDao.updateDictionaryName(dictionaryCode.getId(),dictionaryCode.getName());

        //修改第二个表的数据
        //数据分类
        List<Dictionary> updatedata = new ArrayList<>();
        List<Dictionary> adddata = new ArrayList<>();
        for (Dictionary dictionary:dictionaries) {
            if (dictionary.getId() == 0) {
                dictionary.setCode(dictionaryCode.getCode());
                adddata.add(dictionary);
            } else {
                updatedata.add(dictionary);
            }
        }
        //修改旧数据的
        if (updatedata.size() > 0) {
            dictionaryWriteDao.updateDictionaryOldData(updatedata);
        }
        //新增的数据
        if (adddata.size() > 0) {
            dictionaryWriteDao.updateDictionaryNewData(adddata);
        }

        //将字典数据推送到消息平台
       /* DictionaryMessage dictionaryMessage = new DictionaryMessage();
        dictionaryMessage.update(dictionaryCode);

        if (updatedata.size() > 0) {
            for (Dictionary dic: updatedata) {
                DictionaryItemMessage dictionaryItemMessage = new DictionaryItemMessage();
                dictionaryItemMessage.update(dic);
            }
        }
        addNewToBase(adddata);*/

    }

    /**
     * 新增字典 字典名和字典项。
     * @param dictionaryCode 字典code
     * @param dictionaries 字典列表
     */
    @Override
    @Transactional
    public void addDictionary(Dictionary dictionaryCode, List<Dictionary> dictionaries) {

        dictionaryWriteDao.addNewDictionary(dictionaryCode.getName(),dictionaryCode.getCode());

        //修改第二个表的数据
        //数据分类
        List<Dictionary> adddata = new ArrayList<>();
        for (Dictionary dictionary:dictionaries) {

            dictionary.setCode(dictionaryCode.getCode());
            adddata.add(dictionary);
        }

        //新增的数据
        if (adddata.size() > 0) {
            dictionaryWriteDao.updateDictionaryNewData(adddata);
        }
        //将字典数据推送到消息平台
       /* DictionaryMessage dictionaryMessage = new DictionaryMessage();
        dictionaryCode = dictionaryDao.findDictionaryByCode(dictionaryCode.getCode());
        dictionaryMessage.insert(dictionaryCode);
        addNewToBase(adddata);*/
    }

    /**
     * 新增字典 只有字典名。
     * @param dictionaryCode 字典code
     */
    @Override
    public void addDictionary(Dictionary dictionaryCode) {
        dictionaryWriteDao.addNewDictionary(dictionaryCode.getName(),dictionaryCode.getCode());
    }

    /**
     * 更新字典名称。
     * @param id 主键 name 新名字
     */
    @Override
    public void changeName(Long id, String name) {
        //修改第一个表的数据
        dictionaryWriteDao.updateDictionaryName(id,name);
    }

    /**
     * 更新字典信息到基础数据的抽离方法。
     * @param adddata 新增的数据
     */
    /*public void addNewToBase(List<Dictionary> adddata) {
        if (adddata.size() > 0) {
            for (Dictionary dic: adddata) {
                DictionaryItemMessage dictionaryItemMessage = new DictionaryItemMessage();
                dic = dictionaryDao.findDictionItemByCode(dic.getCode(),dic.getOptionName());
                dictionaryItemMessage.insert(dic);
            }
        }
    }*/
}
