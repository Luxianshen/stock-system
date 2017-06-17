package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18。
 */
public interface MaterialWriteDao extends MaterialDao {

    void addNewMaterial(Materiel material);

    void addNewMetadata(List<Metadata> metadatas);

    void createTable(Materiel material, List<Metadata> data);

    void updateOldMetadata(List<Metadata> updateMetadatas);

    void updateTable(Materiel material, List<Metadata> updatemetadataList);

    void addLineTable(Materiel material, List<Metadata> addMetadatas);
}
