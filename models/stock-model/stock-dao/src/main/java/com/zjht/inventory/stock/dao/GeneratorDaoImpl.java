package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.dao.mapper.GeneratorMapper;
import org.apache.log4j.Logger;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2016/11/29.
 */
@ExportService(serviceTypes = {GeneratorDao.class})
public class GeneratorDaoImpl implements GeneratorDao {
    private GeneratorMapper generatorMapper;
    public void setGeneratorMapper(GeneratorMapper generatorMapper) {
        this.generatorMapper = generatorMapper;
    }

    @ImportService
    InventoryDetailedDao inventoryDetailedDao;

    private Logger logger = Logger.getLogger(getClass());
    private static Map<String,Long> map = new HashMap<>();


    /**
     * 生成资产编号
     * @param largeCategory 大类编码
     * @param smallCategory 小类编码
     * @return
     */
    public synchronized String generateAssetOne(String largeCategory, String smallCategory) {
        String category = largeCategory + smallCategory;
        Long l = map.get(category);
        if(l!=null){
            map.put(category,++l);
        }else{
            /*String assetNo = inventoryDetailedDao.getMaxAssetNoBy(category);
            if(StringUtils.isEmpty(assetNo) || assetNo.length()<4) assetNo = category+"000000";
            try {
                assetNo = assetNo.substring(category.length(),assetNo.length());
                Long no = Long.valueOf(assetNo);
                map.put(category,++no);
            } catch (Exception e) {
                String msg = "数据库存在不规则的资产编号[ " + assetNo + " ], 请联系管理员!";
                logger.error(msg,e);
                throw new RuntimeException(msg,e);
            }*/
        }
        return category+String.format("%06d", map.get(category));
    }

    @Override
    public Long next(String seq) {
        return generatorMapper.next(seq);
    }

    @Override
    public Long nextGeneral() {
        return generatorMapper.nextGeneral();
    }
}
