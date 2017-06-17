package com.zjht.inventory.stock.manager.ui;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.EmployeeDao;
import com.zjht.inventory.stock.dao.ManagerDao;
import com.zjht.inventory.stock.dao.WarehouseInfoDao;
import com.zjht.inventory.stock.dao.WarehouseMaterialDao;
import com.zjht.inventory.stock.entity.Employee;
import com.zjht.inventory.stock.entity.WarehouseInfo;
import com.zjht.inventory.stock.entity.WarehouseMaterial;
import com.zjht.inventory.stock.service.WarehouseInfoService;

/**
 * Created by longshuzhen on 2016/10/13.
 */

@Controller
public class RepertoryController {

    @ImportService
    private WarehouseInfoDao warehouseDao;

    @ImportService
    private WarehouseInfoService warehouseService;

    @ImportService
    private ManagerDao managerDao;

    @ImportService
    private EmployeeDao employeeDao;

    @ImportService
    WarehouseMaterialDao warehouseMaterialDao;

    private int itemOfEachPage = 10;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //仓库列表的获取
    @RequestMapping(value = "/repertory/list.html", method = RequestMethod.GET)
    public String list(ModelMap model, WarehouseInfo warehouse,@RequestParam(value = "index", defaultValue = "1") int index) {

        logger.info("warehouse list ....");
        //获取管理员列表
        List<WarehouseInfo> manList = warehouseDao.findWarehouseKeeperList();
        for (int i = 0 ; i < manList.size(); i++){
            if(manList.get(i).getWarehouseKeeper() == null || manList.get(i).getWarehouseKeeper().equals("null") )
                manList.remove(i);
        }
       // List<Manager> manList = managerDao.findAllManager();
        model.addAttribute("manList", manList);
        warehouse.setWarehouseKeeper("");
        model.addAttribute("warehouse",warehouse);
        //获取表格全部数据
        Page<WarehouseInfo> warehouseList = warehouseDao.FindList(index, itemOfEachPage);
        model.addAttribute("warehouseList", PageInfo.from(warehouseList));
        logger.info("warehouse list load finish...");
        return "repertory/list";
    }

    /**
     * 联合查询仓库列表
     *
     * @param model
     * @param index
     * @return
     */
    @RequestMapping(value = "repertory/searchWarehouse.html", method = RequestMethod.POST)
    public String searchlist(ModelMap model, WarehouseInfo warehouse, @RequestParam(value = "index", defaultValue = "1") int index) {

        logger.info("search warehouse list ....");
        //获取管理员列表
        List<WarehouseInfo> manList = warehouseDao.findWarehouseKeeperList();
        model.addAttribute("manList", manList);
        model.addAttribute("warehouse",warehouse);
        //获取符合条件的全部数据
        Page<WarehouseInfo> warehouseList = warehouseDao.findSomeWarehouse(warehouse, index, itemOfEachPage);
        model.addAttribute("warehouseList", PageInfo.from(warehouseList));
        logger.info("warehouse list load finish...");
        return "repertory/list";
    }

    //新增页面的初始化
    @RequestMapping(value = "/repertory/preadd.html", method = RequestMethod.GET)
    public String preadd() {
        return "repertory/add";
    }

    //新增一个仓库
    @RequestMapping(value = "/repertory/add.html", method = RequestMethod.POST)
    public String add(ModelMap model, WarehouseInfo warehouse, @RequestParam(value = "index", defaultValue = "1") int index) {
        //查找当前仓库列表，列表长度+1作为新增的仓库的id
        Employee employee = employeeDao.getEmployeeId(warehouse.getWarehouseKeeper());
        warehouse.setWarehouseKeeper(employee.getCode());
        List<WarehouseInfo> list = warehouseDao.findAll();
        warehouse.setId(Integer.toUnsignedLong(list.size()+1));
        warehouse.setCreateTime(new Date());
        warehouse.setStatus("1");
        warehouseService.createWarehouse(warehouse);
        //更新页面
        Page<WarehouseInfo> warehouseList = warehouseDao.FindList(index, itemOfEachPage);
        model.addAttribute("warehouseList", PageInfo.from(warehouseList));
        return "redirect:/repertory/list.html";
    }

    //验证仓库名是否已存在
    @RequestMapping(value = "/repertory/nameUnique.html", method = RequestMethod.POST)
    @ResponseBody
    public Object nameUnique(@RequestParam(value = "name")String name){
        WarehouseInfo warehouse = warehouseDao.findWarehouseByName(name);
        if(warehouse != null){
            return false;
        }else {
            return true;
        }
    }


    //更新页面的初始化 包括数据的获取并填充到更新页面
    @RequestMapping(value = "/repertory/preupdate.html", method = RequestMethod.GET)
    public String preupdate(ModelMap model, @RequestParam(value = "id") Long id) {
        WarehouseInfo warehouse = warehouseDao.findWarehouseById(id);
        model.addAttribute("warehouse", warehouse);
        return "repertory/update";
    }

    //更新仓库信息（目前只用来更新管理员）
    @RequestMapping(value = "/repertory/update.html", method = RequestMethod.POST)
    public String update(ModelMap model, @RequestParam(value = "id") Long id, WarehouseInfo warehouse, @RequestParam(value = "index", defaultValue = "1") int index) {
        warehouse.setId(id);
        Employee employee = employeeDao.getEmployeeId(warehouse.getWarehouseKeeper());
        warehouse.setWarehouseKeeper(employee.getCode());
        if(warehouse.getWarehouseKeeper()==null && warehouse.getWarehouseKeeper().equals("null")){
            return "redirect:/repertory/preupdate.html?id="+ id;
        }else{
            warehouseService.updateWarehouse(warehouse);
            //获取表格全部数据
            Page<WarehouseInfo> warehouseList = warehouseDao.FindList(index, itemOfEachPage);
            model.addAttribute("warehouseList", PageInfo.from(warehouseList));
            logger.info("warehouse update finish...");
            return "redirect:/repertory/list.html";
        }
    }

    //删除仓库
    @RequestMapping(value = "/repertory/remove.html", method = RequestMethod.POST)
    @ResponseBody
    public String remove(ModelMap model, String param, WarehouseInfo warehouse, @RequestParam(value = "index", defaultValue = "1") int index) {
        String result = "";
        //获取表格全部数据
        List<WarehouseMaterial> warehouseMaterialList = warehouseMaterialDao.findMetarialByWarehouseId(warehouse.getId());
        if (warehouseMaterialList.size() > 0 && warehouseMaterialList != null) {
            result = "false";
        } else {
            warehouse.setId(warehouse.getId());
            //逻辑删除该仓库，将状态设为0
            warehouse.setStatus("0");
            warehouseService.removeWarehouse(warehouse);
            result = "true";
        }
        return result;

    }


}