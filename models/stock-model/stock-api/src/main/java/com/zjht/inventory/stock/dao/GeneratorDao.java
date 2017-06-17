package com.zjht.inventory.stock.dao;

/**
 * Created by Jason on 2016/10/18.
 */
public interface GeneratorDao {

    /**
     * 生成资产编号
     * @param largeCategory
     * @param smallCategory
     * @return
     */
    String generateAssetOne(String largeCategory, String smallCategory);
    /**
     * 获取自增序列
     * @param seq 序列名
     * @return
     */
    Long next(String seq);

    /**
     * 获取全系统通用自增序列
     * @return
     */
    Long nextGeneral();
}
