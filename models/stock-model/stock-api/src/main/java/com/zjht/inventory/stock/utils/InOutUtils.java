package com.zjht.inventory.stock.utils;

import com.zjht.commons.utils.StatusUtil;
import com.zjht.inventory.stock.dao.InventoryDetailedDao;
import com.zjht.inventory.stock.dao.LockDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.dao.WarehouseMaterialDao;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.InOutDetail;
import com.zjht.inventory.stock.entity.Lock;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.entity.Property;
import com.zjht.inventory.stock.entity.Scheduing;
import com.zjht.inventory.stock.entity.Search;
import com.zjht.inventory.stock.entity.WarehouseMaterial;
import com.zjht.inventory.stock.service.Generator;
import com.zjht.inventory.stock.service.WarehouseMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jason on 2016/11/18.
 */
public class InOutUtils {

    private static Logger logger = LoggerFactory.getLogger(InOutUtils.class);

    /**
     * 梳理字段
     *
     * @param metadatas
     * @param propertyMap
     */
    public static void groomingPropertys(
            ArrayList<Metadata> metadatas, List<LinkedHashMap<String, Object>> propertyMap) {

        //模拟共有字段
        Metadata status = new Metadata("库存状态", "STATUS", "NUMBER", 2, "INVENTORY_STATUS");
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        Iterator<Map.Entry<Long, String>> it = StatusUtil.all.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, String> next = it.next();
            HashMap<String, String> map = new HashMap<>();
            map.put("KEY", next.getValue());
            map.put("VALUE", next.getKey().toString());
            maps.add(map);
        }
        status.setItems(maps);
        metadatas.add(0, status);
        Metadata assetsNo = new Metadata("资产编号", "ASSETS_NO", "VARCHAR2", 64, null);
        metadatas.add(0, assetsNo);


        //状态中文显示处理
        if (propertyMap != null && propertyMap.size() > 0) {
            for (LinkedHashMap<String, Object> link : propertyMap) {
                Iterator<Map.Entry<String, Object>> iterator = link.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    if ("STATUS".equals(next.getKey())) {
                        Object value = next.getValue();
                        value = Long.parseLong(value.toString());
                        next.setValue(StatusUtil.all.get(value));
                    }
                }
            }
        }
    }

    /**
     * 拼装查询sql
     *
     * @param search       查询的参数集合
     * @param privateTable 查询的私有属性表明
     * @param metadatas    元数据集合
     * @return 可执行语句
     */
    public static String getSearchSql(
            Search search, String privateTable, ArrayList<Metadata> metadatas) {

        //设置数据库表别名
        String tt = "tt";//私有属性表别名
        String id = "id";//共有属性表别名
        String alias = "tt";//共有属性表别名

        StringBuilder columnSb = new StringBuilder("");
        if (metadatas == null || metadatas.size() <= 0) {
            //columnSb.append("," + tt + ".*");
        } else {
            for (Metadata metadata : metadatas) {
                columnSb.append("," + tt + "." + metadata.getCode());
            }
        }

        //查询所有语句部分
        String sql =
            "select " + id + ".ASSETS_NO," + id + ".STATUS" + columnSb.toString() + " from " +
            privateTable + " " + tt + " " + "right join T_INVENTORY_DETAILED " + id + " on " + tt +
            ".INVENTORY_DETAILED_ID=" + id + ".ID";


        if (StringUtils.isEmpty(privateTable) || "NULL".equalsIgnoreCase(privateTable)) {
            sql = "select " + id + ".ASSETS_NO," + id + ".STATUS" + " from " +
                  "T_INVENTORY_DETAILED " + id;
        }

        if (search == null || search.getMaps() == null || search.getMaps().size() <= 0) {
            return sql;
        }


        //拼装条件搜索部分
        StringBuilder sb = new StringBuilder(" where 1=1");
        for (Map<String, Object> map : search.getMaps()) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            if (!it.hasNext()) {
                continue;
            }
            Map.Entry<String, Object> next = it.next();
            String key = next.getKey();
            Object value = next.getValue();
            if (!StringUtils.isEmpty(value)) {
                if (key.equalsIgnoreCase("ASSETS_NO") || key.equalsIgnoreCase("STATUS") ||
                    key.equalsIgnoreCase("WAREHOUSE_MATERIAL_ID")) {
                    alias = id;
                } else {
                    alias = tt;
                }
                if (value instanceof ArrayList && ((ArrayList) value).size() > 0) {
                    String str = value.toString();
                    String sub = str.substring(1, str.length() - 1);
                    sb.append(" and " + alias + "." + key + " in (" + sub + ")");
                } else {
                    sb.append(" and " + alias + "." + key + " = '" + value + "'");
                }
            }
        }

        //组合条件
        sql += sb.toString();
        return sql;
    }


    /**
     * 提取出入库记录
     *
     * @param inOutType
     * @param applyId
     * @param orderId
     * @param username
     * @param warehouseId
     * @param drawingPerson
     */
    public static Scheduing extractScheduing(
        Long id, Long inOutType, Long applyId, String orderId, String username, Long warehouseId,
        String drawingPerson) {
        String systemNo = (new Random().nextInt(9)) + "" + (System.currentTimeMillis());
        Scheduing scheduing = new Scheduing(id, systemNo, inOutType, applyId, orderId, username,
                                            new Date(), drawingPerson/*领机人*/, warehouseId);
        return scheduing;
    }


    /**
     * 提取出入库明细(计件数量1)
     *
     * @param systemNo
     * @param materielId
     * @param inventoryDetailedId
     * @param operateNum
     * @return
     */
    public static InOutDetail extractInOutDetail(
        Long id, String systemNo, Long materielId, Long inventoryDetailedId, Long operateNum) {
        return new InOutDetail(id, systemNo, materielId, inventoryDetailedId, operateNum);
    }

    /**
     * 拼接SQL
     *
     * @param warehouseMaterialId 仓库物料编号
     * @param table               库存明细表
     * @param properties          属性集合
     * @param ids                 排除在外的库存明细编号
     * @param <T>                 属性集合对象
     * @return 查询语句
     */
    public static <T extends Property> String getCalculateInventorySql(
        Long warehouseMaterialId, String table, ArrayList<T> properties, ArrayList<Long> ids) {
        StringBuilder sb = new StringBuilder("select t.INVENTORY_DETAILED_ID from " + table +
                                             " t inner join T_INVENTORY_DETAILED idl" +
                                             " on idl.ID = t.INVENTORY_DETAILED_ID" +
                                             " where idl.STATUS = " + StatusUtil.STOCK_NORMAL +
                                             " and idl.WAREHOUSE_MATERIAL_ID = " +
                                             warehouseMaterialId);
        if (properties != null && properties.size() > 0) {
            for (Property p : properties) {
                String property = p.getProperty();
                String value = p.getValue();
                sb.append(" and t." + property + " = " + "'" + value + "'");
            }
        }
        if (ids != null && ids.size() > 0) {
            sb.append(" and t.INVENTORY_DETAILED_ID NOT IN(" +
                      ids.toString().substring(1, ids.toString().length() - 1) + ")");
        }
        return sb.toString();
    }


    public static <T> Map<String, String> keyToStringMap(Map<T, String> all) {
        Iterator<Map.Entry<T, String>> it = all.entrySet().iterator();
        Map<String, String> map = new HashMap();
        while (it.hasNext()) {
            Map.Entry<T, String> next = it.next();
            map.put(next.getKey().toString(), next.getValue());
        }
        return map;
    }

    /**
     * 申请单检查库存
     *
     * @param apply     申请单
     * @param generator
     * @return true:库存充足 false: 库存不足
     */
    public static String checkTheInventory(
            Apply apply, Generator generator, InventoryDetailedDao inventoryDetailedDao,
            MaterialDao materialDao, WarehouseMaterialDao warehouseMaterialDao, LockDao lockDao,
            WarehouseMaterialService warehouseMaterialService) {
        boolean isOut = StatusUtil.isInOut(StatusUtil.outs, apply.getApplyType());
        boolean isLock = StatusUtil.STOCK_LOCK == apply.getApplyType();
        boolean is = isOut || isLock;
        if (!is) {
            return null;//申请出库和锁定时才继续检测,否则表示充足
        }
        //排除计算库存的库存明细编号集合
        ArrayList<Long> ids = new ArrayList<>();
        //仓库编号
        Long warehouseBelong = apply.getWarehouseBelong();
        ArrayList<ApplyList> applyLists = apply.getApplyLists();
        for (ApplyList applyList : applyLists) {
            Long materielId = applyList.getMaterielId();
            Materiel materiel = materialDao.findById(materielId);
            long count = applyList.getCount();
            WarehouseMaterial warehouseMaterial = warehouseMaterialDao.findByWarehouseIdAndMaterialId(
                warehouseBelong, materielId);
            if (warehouseMaterial == null) {
                try {
                    warehouseMaterialService.insertOne(generator.next(WarehouseMaterial.SEQ),
                                                       warehouseBelong, materielId, "");
                    warehouseMaterial = warehouseMaterialDao.findByWarehouseIdAndMaterialId(
                        warehouseBelong, materielId);
                } catch (Exception exception) {
                    logger.error("创建仓库物料表数据失败", exception.getMessage());
                    return "<i class='icon-remove-sign'></i> 错误!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库未能记录[" + materiel.getName() + "]的库存（仓库物料）数量";
                }
            }
            Number number = warehouseMaterial.getInventoryCount();//库存记录数
            if (number == null || number.longValue() < count) {
                String msg =
                    "库存不足!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库记录的[" + materiel.getName() + "]库存数量[" + number.longValue() + "]低于申请数量[" +
                    count + "]";
                logger.error(msg);
                return msg;//库存不足
            }
            ArrayList<Lock> locks = lockDao.findByWarehouseMaterial(warehouseBelong, materielId);
            long sum = locks.stream().mapToLong(l -> l.getLockNum()).sum();//已锁定数量
            if (number.longValue() < count + sum) {
                return "库存不足!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库[" + materiel.getName() + "]记录的库存数量[" + number.longValue() + "]（锁定数量[" +
                       sum + "]）低于申请数量[" + count + "]";//库存不足
            }
            //接下来属性字段参与计算判断实际库存
            if (materiel.getType() == Materiel.QUANTITATIVE ||
                StringUtils.isEmpty(materiel.getMappingPrivateTable())) {
                return null;//计量的到此为止
            }
            //返回查询 此次符合条件的库存明细编号列表 的SQL
            String sql = "";
            ArrayList<Long> longs = new ArrayList();
            //获取属性值
            String result = "";
            if (applyList.getApplyListProperties() != null &&
                applyList.getApplyListProperties().size() > 0) {
                for (int i = 0; i < applyList.getApplyListProperties().size(); i++) {
                    if (i == (applyList.getApplyListProperties().size() - 1)) {
                        result = result + /*applyList.getApplyListProperties().get(i).getProperty() +
                                 " : " + */applyList.getApplyListProperties().get(i).getValue();
                    } else {
                        result = result + /*applyList.getApplyListProperties().get(i).getProperty() +
                                 " : " + */applyList.getApplyListProperties().get(i).getValue() +
                                 "、 ";
                    }
                }
            }
            for (Lock lock : locks) {
                sql = InOutUtils.getCalculateInventorySql(warehouseMaterial.getId(),
                                                          materiel.getMappingPrivateTable(),
                                                          lock.getLockProperties(), ids);
                try {
                    //longs = inventoryDetailedDao.executeSqlFindIds(sql);
                } catch (Exception e) {
                    String msg = "<i class='icon-remove-sign'></i> 错误!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询[" + materiel.getName() + "]属性为{" + result + "}的实际库存数量出现错误";
                    logger.error(msg, e);
                    return msg;
                }
                if (longs == null || longs.size() <= 0) {
                    return "库存不足!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库[" + materiel.getName() + "]属性为{" + result + "}的实际库存数量[" +
                           longs.size() + "]低于申请数量[" + count + "]";
                }
                ids.add(longs.get(0));
            }
            sql = InOutUtils.getCalculateInventorySql(warehouseMaterial.getId(),
                                                      materiel.getMappingPrivateTable(),
                                                      applyList.getApplyListProperties(), ids);
            try {
                //longs = inventoryDetailedDao.executeSqlFindIds(sql);
            } catch (Exception e) {
                String msg = "<i class='icon-remove-sign-sign'></i> 错误!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库["+materiel.getName()+"]属性为{"+result+"}的实际库存数量出现错误";
                logger.error(msg, e);
                return msg;//没有这种字段的物料响应无库存
            }
            if (longs == null || longs.size() < count) {
                return "<i class='icon-exclamation-sign'></i> 库存不足!<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请仓库["+materiel.getName()+"]属性为{"+result+"}的实际库存数量["+longs.size()+"]低于申请数量["+count+"]（包含锁定单）";
            }
            ids.add(longs.get(0));
        } return null;
    }
}
