package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18。
 */
public interface MaterialService {

    void addNewMaterial(Materiel material, List<Metadata> metadataList);

    void updateOldMaterial(Materiel material, List<Metadata> dataList);

}
