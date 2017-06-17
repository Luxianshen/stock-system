package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.dao.MaterialWriteDao;
import com.zjht.inventory.stock.dao.MetadataDao;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * lujiasheng 2017/01/11。
 */
@ExportService(serviceTypes = {MaterialService.class})
public class MaterialServiceImpl implements MaterialService {

    @ImportService
    private MaterialWriteDao materialWriteDao;
    @ImportService
    private MaterialDao materialDao;
    @ImportService
    private MetadataDao metadataDao;

    /**
     * 新增物料的 数据处理 准备调用dao存储。
     * @param material 物料
     * @param metadataList 元数据列表
     */
    @Override
    @Transactional
    public void addNewMaterial(Materiel material, List<Metadata> metadataList) {
        //先获取ID
        Long materialId = materialDao.getAddId();
        material.setId(materialId);
        List<Metadata> metadatas = new ArrayList<>();
        for (Metadata meta : metadataList) {
            meta.setMaterielId(materialId);
            if (meta.getDictionaryCode().isEmpty() || meta.getDictionaryCode() == null ||
                    meta.getDictionaryCode().equals("null")) {
                meta.setDictionaryCode("NODICTIONARY");
            }
            metadatas.add(meta);
        }
        if (material.getType() != 1) {
            //先执行createTable
            materialWriteDao.createTable(material, metadatas);
            //创建表成功后，保存数据
            materialWriteDao.addNewMaterial(material);
            materialWriteDao.addNewMetadata(metadatas);
            //List<Metadata> data = materialDao.findMeteDataName(materialId);
        }else {
            materialWriteDao.addNewMaterial(material);
        }

        //将物料信息生产推送给消息平台
        /*MaterialMessage materialMessage = new MaterialMessage();
        material = materialDao.findMaterialByCode(material.getCode());
        materialMessage.insert(material);
        //将元数据信息生产推送给消息平台
        for (Metadata meta: metadataList) {
            MetadataMessage metadataMessage = new MetadataMessage();
            meta = metadataDao.findMetadataByMaterialIdAndCode(material.getId(),meta.getCode());
            metadataMessage.insert(meta);
        }*/
    }

    /**
     * 物料更新 数据准备 调用dao储存。
     * @param material 物料
     * @param dataList 数据列表
     */
    @Override
    @Transactional
    public void updateOldMaterial(Materiel material, List<Metadata> dataList) {

        //处理数据
        List<Metadata> updateMetadatas = new ArrayList<>();
        List<Metadata> addMetadatas = new ArrayList<>();

        for (Metadata meta:dataList) {
            if (meta.getId() != null && meta.getId() != 0) {
                updateMetadatas.add(meta);
            } else {
                meta.setMaterielId(material.getId());
                if (meta.getDictionaryCode().isEmpty() || meta.getDictionaryCode() == null ||
                        meta.getDictionaryCode().equals("null")) {
                    meta.setDictionaryCode("NODICTIONARY");
                }
                addMetadatas.add(meta);
            }
        }

        //插入更新数据
        //更新元数据表数据 1.更新已有数据 2.插入新数据
        if (updateMetadatas.size() > 0 && updateMetadatas != null) {
            materialWriteDao.updateOldMetadata(updateMetadatas);
            //组装sql 更新table
            materialWriteDao.updateTable(material,updateMetadatas);
        }
        if (addMetadatas.size() > 0 && addMetadatas != null) {
            materialWriteDao.addNewMetadata(addMetadatas);
            materialWriteDao.addLineTable(material,addMetadatas);
        }

        //将元数据信息生产推送给消息平台
        /*if (updateMetadatas.size() > 0 && updateMetadatas != null) {
            for (Metadata meta: updateMetadatas) {
                MetadataMessage metadataMessage = new MetadataMessage();
                metadataMessage.update(meta);
            }
        }
        if (addMetadatas.size() > 0 && addMetadatas != null) {
            for (Metadata meta: addMetadatas) {
                MetadataMessage metadataMessage = new MetadataMessage();
                material = materialDao.findMaterialByCode(material.getCode());
                meta = metadataDao.findMetadataByMaterialIdAndCode(material.getId(),meta.getCode());
                metadataMessage.insert(meta);
            }
        }*/
    }

}
