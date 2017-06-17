package com.zjht.inventory.stock.manager.ui;


import com.zjht.inventory.stock.dao.InventoryDetailedDao;
import com.zjht.inventory.stock.dao.InventoryDetailedWriteDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.dao.MetadataDao;
import com.zjht.inventory.stock.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * Created by Jason on 2016/10/12.
 */

/**
 * 库存明细控制器
 */

@Controller
public class InventoryDetailController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ImportService
    private MetadataDao metadataDao;
    @ImportService
    private MaterialDao materialDao;
    @ImportService
    private InventoryDetailedDao inventoryDetailedDao;
    @ImportService
    private InventoryDetailedWriteDao inventoryDetailedWriteDao;


    /**
     * 库存列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/inventory/list.html", method = RequestMethod.GET)
    public String detail(Model model, Long materialId) {


        materialId =  283L;

        Materiel material = materialDao.findById(materialId);

        /**
         *传入元数据的MAPPING_PRIVATE_TABLE ID
         * 用于查找该分类的元数据 确定<th>
         */
        //需要物料主键 MATERIEL_ID   接口传入
        //查找的私有属性表的表名 table_name

        //根据MATERIEL_ID 查找属性名称
        ArrayList<Metadata> metadatas = metadataDao.findMetadataByMaterialId(materialId);


        //查找私有属性的表名
        String table_name = material.getMappingPrivateTable();

        //map获取属性数据
        List<LinkedHashMap<Long, Object>> propertyMap = metadataDao.findInventoryDetailBy(null, table_name);

        removePropertys(metadatas, propertyMap);

        //传数值到界面
        model.addAttribute("metadatas", metadatas);
        model.addAttribute("propertyMap", propertyMap);
        model.addAttribute("materialId", materialId);
        model.addAttribute("STOCK_NORMAL", Status.STOCK_NORMAL);
        model.addAttribute("STOCK_LOCK", Status.STOCK_LOCK);
        return "inventory/list";
    }

    /**
     * 去除不需要的字段
     * @param metadatas
     * @param propertyMap
     */
    public static void removePropertys(ArrayList<Metadata> metadatas, List<LinkedHashMap<Long, Object>> propertyMap) {

        for (int i=0; i<metadatas.size(); i++){
            String code = metadatas.get(i).getCode();
            if("ID".equals(code)){
                metadatas.remove(i--);
            }
        }

        for (HashMap<Long,Object> map : propertyMap){
            Iterator<Map.Entry<Long, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, Object> next = it.next();
                if ("ID".equals(next.getKey())) {
                    it.remove();
                }
            }
        }

        Metadata status = new Metadata("库存状态","STATUS","NUMBER",2,"inventory_status");
        metadatas.add(0,status);
        Metadata assetsNo = new Metadata("资产编号","ASSETS_NO","VARCHAR2",64,null);
        metadatas.add(0,assetsNo);
    }

    /**
     * @条件搜索 库存列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/inventory/search.html", method = RequestMethod.POST)
    public String search(Model model, Search search, Long materialId) {

        //获取物料
        Materiel material = materialDao.findById(materialId);

        //获取元数据列表
        ArrayList<Metadata> metadatas = metadataDao.findMetadataByMaterialId(material.getId());

        //拼装查询sql
        String sql = getSearchSql(search, material.getMappingPrivateTable());

        //获取属性<名、值>集合
        //List<LinkedHashMap<Long, Object>> propertyMap = metadataDao.findDataBySql(sql);

        //去除不显示字段
        //removePropertys(metadatas, propertyMap);

        //传数值到界面
        model.addAttribute("metadatas", metadatas);
        //model.addAttribute("propertyMap", propertyMap);
        model.addAttribute("materialId", materialId);
        model.addAttribute("STOCK_NORMAL", Status.STOCK_NORMAL);
        model.addAttribute("STOCK_LOCK", Status.STOCK_LOCK);
        return "inventory/list";
    }

    /**
     * 拼装查询sql
     * @param search 查询的参数集合
     * @param privateTable 查询的私有属性表明
     * @return 可执行语句
     */
    private String getSearchSql(Search search, String privateTable) {

        //设置数据库表别名
        String tt = "tt";//私有属性表别名
        String idl = "idl";//共有属性表别名

        //查询所有语句部分
        String sql = "select " + idl + ".ASSETS_NO," + idl + ".STATUS," + tt + ".* from " +
                privateTable + " " + tt + " " +
                "left join T_INVENTORY_DETAILED " + idl + " on " + tt + ".INVENTORY_DETAILED_ID=" + idl + ".ID";

        if(search==null || search.getMaps()==null || search.getMaps().size()<=0) return sql;


        //拼装条件搜索部分
        StringBuilder sb = new StringBuilder(" where 1=1");
        String alias = tt;
        for (Map<String,Object> map : search.getMaps()){
            Map.Entry<String, Object> next = map.entrySet().iterator().next();
            String key = next.getKey();
            Object value = next.getValue();
            if(!StringUtils.isEmpty(value)){
                if(key.equalsIgnoreCase("ASSETS_NO") ||  key.equalsIgnoreCase("STATUS")) alias = idl;
                sb.append(" and " + alias + "." + key + " = " + value);
            }
        }

        //组合条件
        sql += sb.toString();
        return sql;
    }


    /**
     * 锁定/解锁
     * @param model
     * @param materialId 物料主键
     * @param detailedId 明细主键
     * @return
     */
    @RequestMapping(value = "/inventory/lockAndUnlock.html", method = RequestMethod.GET)
    @ResponseBody
    public Object lockAndUnlock(Model model, Long materialId, Long detailedId) {
        Materiel material = materialDao.findById(materialId);
        InventoryDetailed idl = inventoryDetailedDao.findById(detailedId,material.getMappingPrivateTable());
        Number status = idl.getStatus();
        if(status.longValue()==Status.STOCK_NORMAL){
            inventoryDetailedWriteDao.changeStatusById(detailedId,Status.STOCK_LOCK);
        }else if(status.longValue()==Status.STOCK_LOCK){
            inventoryDetailedWriteDao.changeStatusById(detailedId,Status.STOCK_NORMAL);
        }else{
            return 0;//状态不符合锁定/解锁要求 错误
        }
        return 1;//锁定/解锁 成功
    }
}
