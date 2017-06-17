package com.zjht.inventory.stock.manager.ui;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.InventoryDetailedDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.dao.MetadataDao;
import com.zjht.inventory.stock.dao.ScheduingDao;
import com.zjht.inventory.stock.entity.*;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jason on 2016/10/24.
 */
@Controller
public class ScheduingController {

    @ImportService
    private MaterialDao materialDao;
    @ImportService
    private MetadataDao metadataDao;

    @ImportService
    private InventoryDetailedDao inventoryDetailedDao;

    /**
     * 出入库Dao
     */
    @ImportService
    private ScheduingDao scheduingDao;


    @RequestMapping(value = "/scheduing/in", method = RequestMethod.GET)
    public String in(Model model, Long materielId) {
        Materiel material = materialDao.findById(materielId);
        ArrayList<Metadata> metadatas = metadataDao.findMetadataByMaterialId(material.getId());

        return null;
    }

    /**
     * 导出Excel
     * @param model
     * @param scheduing
     * @param startTime
     * @param endTime
     */
    @RequestMapping(value = "/scheduing/derive")
    public void derive(Model model, Scheduing scheduing, String startTime, String endTime) {

    }

    @RequestMapping(value = "/scheduing/detail")
    public String detail(Model model, String systrace) {
        //查找出入库记录
        Scheduing scheduing = scheduingDao.findBySystrace(systrace);
        //查找出入库明细
        ScheduingDetail detail = scheduingDao.findDetailBySystrace(systrace);
        //查找库存明细
        Long materielId = detail.getMaterielId();
        Materiel material = materialDao.findById(materielId);

        ArrayList<Metadata> metadatas = metadataDao.findMetadataByMaterialId(material.getId());

        /*List<LinkedHashMap<Long, Object>> propertyMap = metadataDao.findInventoryDetailBy(
                detail.getInventoryDetailedId(), material.getMappingPrivateTable());

        InventoryDetailController.removePropertys(metadatas,propertyMap);*/


        //保存数据
        model.addAttribute("scheduing",scheduing);//出入库记录
        model.addAttribute("detail",detail);//出入库明细
        model.addAttribute("metadatas",metadatas);//库存明细
        //model.addAttribute("propertyMap",propertyMap);//库存明细
        return "scheduing/details";
    }

    @RequestMapping(value = "/scheduing/record")
    public String record(Model model, Scheduing scheduing, String startTime, String endTime,
                         @RequestParam(value = "index",defaultValue = "1") int index,
                         @RequestParam(value = "size",defaultValue = "20") int size) {
        Page<Scheduing> scheduings = scheduingDao.findByPage(scheduing.getApplyId(),scheduing.getOrderId(),scheduing.getInOutType(),
                scheduing.getOperator(),scheduing.getDrawingPerson(),startTime,endTime,index, size);
        model.addAttribute("scheduings", PageInfo.from(scheduings));
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("scheduing", scheduing);
        return "scheduing/list";
    }
}
