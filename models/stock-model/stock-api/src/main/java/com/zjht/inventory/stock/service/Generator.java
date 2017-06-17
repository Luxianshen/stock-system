package com.zjht.inventory.stock.service;

/**
 * Created by Jason on 2016/10/18.
 */
public interface Generator {

    /**
     * 生成资产编号
     * @param categoryId 物料所属分类
     * @return
     */
    String generateAssetOne(Long categoryId);
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
