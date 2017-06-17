package com.zjht.inventory.stock.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.MaterialMapper;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;
import org.smarabbit.massy.annotation.ExportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lujiasheng on 2016/10/18。
 */
@ExportService(serviceTypes = {MaterialDao.class, MaterialWriteDao.class})
public class MaterialDaoImpl implements MaterialWriteDao {

    private MaterialMapper materialMapper;

    /**
     * 物料列表实现具体方法。
     *
     * @param index 主页数
     * @param size  条数
     * @return 物料列表
     */
    @Override
    public Page<Materiel> findList(int index, int size) {
        PageHelper.startPage(index, size);
        return materialMapper.findList();
    }

    /**
     * 根据ID查找数据。
     *
     * @param id ID
     * @return 物料
     */
    @Override
    public Materiel findById(Long id) {
        return materialMapper.findById(id);
    }

    @Override
    public List<Metadata> findMeteDataName(Long materielCategoryId) {
        return materialMapper.findMeteDataName(materielCategoryId);
    }

    public void setMaterialMapper(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    /**
     * 根据仓库ID查找物料记录。
     *
     * @param warehouseId 仓库ID
     * @return 物料列表
     */
    @Override
    public List<Materiel> fineMaterialByWarehouseId(Long warehouseId) {
        return materialMapper.fineMaterialByWarehouseId(warehouseId);
    }

    /**
     * 根据物料分类ID查询物料列表。
     *
     * @param id 主键
     * @return 物料列表
     */
    @Override
    public List<Materiel> findByCategoryId(Long id) {
        return materialMapper.findByCategoryId(id);
    }

    /**
     * 根据查找的参数查询物料。
     *
     * @param searchcode 查询code
     * @return 物料列表
     */
    @Override
    public List<Materiel> findBySearchCode(String searchcode) {
        return materialMapper.findBySearchCode(searchcode);
    }

    @Override
    public List<Materiel> findByCategory() {
        return materialMapper.findByCategory();
    }

    /**
     * 获取新增的序列ID。
     *
     * @return ID
     */
    @Override
    public Long getAddId() {
        return materialMapper.getAddId();
    }

    /**
     * 根据物料ID查询物料元数据。
     *
     * @param materialId 物料ＩＤ
     * @return 物料列表
     */
    @Override
    public List<Metadata> findMeteData(Long materialId) {
        return materialMapper.findMeteData(materialId);
    }

    /**
     * 查询物料code。
     *
     * @return 物料列表
     */
    @Override
    public List<Materiel> findMaterielCode() {
        return materialMapper.findMaterielCode();
    }

    @Override
    public Materiel findByTable(String table) {
        return materialMapper.findByTable(table);
    }

    /**
     * 查询物料查询列表。
     *
     * @param index    主页数
     * @param size     条数
     * @param material 物料
     * @return 物料列表
     */
    @Override
    public Page<Materiel> searchList(int index, int size, Materiel material) {
        PageHelper.startPage(index, size);
        return materialMapper.searchList(material);
    }

    /**
     * 新增物料。
     *
     * @param material 物料
     */
    @Override
    public void addNewMaterial(Materiel material) {
        materialMapper.addNewMaterial(material);
    }

    /**
     * 新增物料元数据。
     *
     * @param metadatas 元数据
     */
    @Override
    public void addNewMetadata(List<Metadata> metadatas) {
        for (int i = 0; i < metadatas.size(); i++) {
            if (metadatas.get(i).getDictionaryCode().equals("NODICTIONARY")) {
                materialMapper.addNewMetadataNoDictionary(metadatas.get(i));
            } else {
                materialMapper.addNewMetadata(metadatas.get(i));
            }
        }
    }

    /**
     * 创建物料私有Table。
     *
     * @param material 物料
     * @param data     数据
     */
    @Override
    public void createTable(Materiel material, List<Metadata> data) {
        // 拼接sql 创建表格
        String sql1 = "create table ";
        String sql2 = material.getMappingPrivateTable() + " ( ";
        String sql3 = "ID NUMBER(20), INVENTORY_DETAILED_ID NUMBER(20), ";
        String sql4 = "";
        String sql = "";

        for (Metadata meta : data) {
            if (meta.getDataType().equals("DATE")) {
                sql3 += meta.getCode() + " DATE NULL ,";
            } else {
                if (meta.getDataType().equals("VARCHAR2")) {
                    sql3 += meta.getCode() + " " + meta.getDataType() + "(" + meta.getLength() * 4L + "),";
                } else {
                    sql3 += meta.getCode() + " " + meta.getDataType() + "(" + meta.getLength() + "),";
                }
            }
        }
        sql = sql1 + sql2 + sql3 + "primary key (ID))";
        // 组装注释
        for (Metadata meta : data) {
            sql4 += "COMMENT ON COLUMN " + material.getMappingPrivateTable() + ".\""
                    + meta.getCode().toUpperCase() + " \" IS '" + meta.getName() + "' ;";
        }
        // COMMENT ON COLUMN T_TEST_POS."ID" IS '主键';
        materialMapper.createTable(sql);

    }

    /**
     * 更新物料员数据。
     *
     * @param updateMetadatas 更新的数据
     */
    @Override
    public void updateOldMetadata(List<Metadata> updateMetadatas) {
        for (int i = 0; i < updateMetadatas.size(); i++) {
            materialMapper.updateOldMetadata(updateMetadatas.get(i));
        }
    }

    /**
     * 更新私有Table旧数据。
     *
     * @param material        物料
     * @param updateMetadatas 更新的元数据
     */
    @Override
    public void updateTable(Materiel material, List<Metadata> updateMetadatas) {

        String sql1 = "ALTER TABLE " + material.getMappingPrivateTable();
        String sql2 = "";
        String sql3 = "";
        String updateTableSql = "";

        // 组装修改旧数据sql
        int length = updateMetadatas.size();
        for (Metadata meta : updateMetadatas) {
            if (meta.getDataType().equals("DATE")) {
                return;
            } else {
                if (length == 1) {
                    if (meta.getLength() != null) {
                        if (meta.getDataType().equals("VARCHAR2")) {
                            sql2 += " MODIFY (" + "\"" + meta.getCode().toUpperCase() + "\" " +
                                    meta.getDataType() + "(" + meta.getLength() * 4L + "))";
                        } else {
                            sql2 += " MODIFY (" + "\"" + meta.getCode().toUpperCase() + "\" " +
                                    meta.getDataType() + "(" + meta.getLength() + "))";
                        }

                    }
                } else {
                    if (meta.getLength() != null) {
                        if (meta.getDataType().equals("VARCHAR2")) {
                            sql2 += " MODIFY (" + "\"" + meta.getCode().toUpperCase() + "\" " +
                                    meta.getDataType() + "(" + meta.getLength() * 4L + "))";
                        } else {
                            sql2 += " MODIFY (" + "\"" + meta.getCode().toUpperCase() + "\" " +
                                    meta.getDataType() + "(" + meta.getLength() + "))";
                        }

                    }
                }
            }
            length--;
        }

        updateTableSql = sql1 + sql2;
        // 单独执行sql3

        if (updateTableSql.equals(sql1)) {
            // 只有sql语句开头，没有更新的内容
        } else {
            materialMapper.updateTable(updateTableSql);
        }

        for (Metadata meta : updateMetadatas) {
            if (meta.getName() != null && meta.getName() != "") {
                sql3 = "COMMENT ON COLUMN " + material.getMappingPrivateTable() + ".\""
                        + meta.getCode().toUpperCase() + "\" IS '" + meta.getName() + "'";
                materialMapper.updateTable(sql3);
            }
        }

    }

    /**
     * 更新私有Table新的元数据。
     *
     * @param material     物料
     * @param addMetadatas 新增的元数据
     */
    @Override
    public void addLineTable(Materiel material, List<Metadata> addMetadatas) {

        String sql1 = "ALTER TABLE " + material.getMappingPrivateTable();
        String sql2 = " ADD (";
        String sql3 = "";
        String sql4 = "";
        String addTableSql = "";

        //ALTER TABLE T_TEST_POS MODIFY ( "ID" NUMBER(19) ) ADD ( "TEST" VARCHAR2(30) NULL )。
        int length = addMetadatas.size();
        // 判断是否只有一条数据。
        for (Metadata meta : addMetadatas) {
            //DATE特殊处理
            if (meta.getDataType().equals("DATE")) {
                if (length == 1) {
                    if (meta.getLength() != null) {
                        sql3 += "\"" + meta.getCode().toUpperCase() + "\" " + meta.getDataType();
                    }
                } else {
                    if (meta.getLength() != null) {
                        sql3 += "\"" + meta.getCode().toUpperCase() + "\" "
                                + meta.getDataType() + ",";
                    }
                }
            } else {
                //不是DATE 一般处理
                if (length == 1) {
                    if (meta.getLength() != null) {
                        if (meta.getDataType().equals("VARCHAR2")) {
                            sql3 += "\"" + meta.getCode().toUpperCase() + "\" " + meta.getDataType()
                                    + "(" + meta.getLength() * 4L + ")";
                        } else {
                            sql3 += "\"" + meta.getCode().toUpperCase() + "\" " + meta.getDataType()
                                    + "(" + meta.getLength() + ")";
                        }
                    }
                } else {
                    if (meta.getLength() != null) {
                        if (meta.getDataType().equals("VARCHAR2")) {
                            sql3 += "\"" + meta.getCode().toUpperCase() + "\" "
                                    + meta.getDataType() + "(" + meta.getLength() * 4L + "),";
                        } else {
                            sql3 += "\"" + meta.getCode().toUpperCase() + "\" "
                                    + meta.getDataType() + "(" + meta.getLength() + "),";
                        }

                    }
                }
            }

            length--;
        }

        addTableSql = sql1 + sql2 + sql3 + ")";
        // sql4单独执行
        materialMapper.updateTable(addTableSql);

        for (Metadata meta : addMetadatas) {
            if (meta.getName() != null && meta.getName() != "") {
                sql4 = "COMMENT ON COLUMN " + material.getMappingPrivateTable() + ".\""
                        + meta.getCode().toUpperCase() + "\" IS '" + meta.getName() + "'";
                materialMapper.updateTable(sql4);
            }
        }
    }

    /**
     * 查询物料列表。
     *
     * @return 物料列表
     */
    @Override
    public ArrayList<Materiel> findMaterialList() {
        return materialMapper.findMaterialList();
    }

    @Override
    public List<Materiel> findByParams(Map<String, Object> params) {
        return materialMapper.findByParams(params);
    }

    /**
     * 用以查询物料列表接口调用。
     *
     * @param id ID
     * @return 物料列表
     */
    @Override
    public List<Materiel> findByCategoryIdForInterface(Long id) {
        return materialMapper.findByCategoryIdForInterface(id);
    }

    /**
     * 根据表名判断该表是否存在数据库中。
     *
     * @param tableName 表名
     * @return 数据
     */
    @Override
    public List<Map<String, String>> getTableInfo(String tableName) {
        return materialMapper.getTableInfo(tableName);
    }

    /**
     * 根据物料ID列表 查询一系列的物料信息。
     *
     * @param materielList 物料列表
     * @return 物料列表
     */
    @Override
    public List<Materiel> findByMaterielIds(ArrayList<String> materielList) {
        return materialMapper.findByMaterielIds(materielList);
    }

    /**
     * 根据物料code查询物料。
     *
     * @param code code
     * @return 物料
     */
    @Override
    public Materiel findMaterialByCode(String code) {
        return materialMapper.findMaterialByCode(code);
    }
}
