package com.zjht.inventory.stock.manager.ui;

import java.util.List;
import java.util.Map;

import com.zjht.inventory.stock.dao.MaterialDao;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.WarehouseInfoDao;
import com.zjht.inventory.stock.dao.WarehouseMaterialDao;
import com.zjht.inventory.stock.entity.WarehouseInfo;

/**
 * Created by longshuzhen on 2016/10/20.
 */
@Controller
public class WarehouseMaterialController {

    @ImportService
    WarehouseMaterialDao warehouseMaterialDao;
    @ImportService
    WarehouseInfoDao warehouseDao;
    @ImportService
    MaterialDao materialDao;

    /**
     * 所有仓库所有物料的统计列表
     * @param model
     * @param index
     * @return
     */
    @RequestMapping(value = "/statistics/list.html", method = RequestMethod.GET)
    public String getMaterialList(Model model, @RequestParam(value = "index", defaultValue = "1") int index,ShiroHttpServletRequest request) {

        //获取仓库名列表
        List<WarehouseInfo> warehouseInfoList = warehouseDao.findAll();

        //根据管理员的不同，统计不同的仓库  默认系统管理员与仓库管理员不是同一个人
        //系统管理员  统计所有
        //仓库管理员  统计该管理员管理的仓库
        Page<Map> materialList;
        //获取当前登录的用户
        //CasLoginUserInfo  casLoginUser = (CasLoginUserInfo)request.getSession().getAttribute(CasShiroFilter.CURRENT_LOGIN_USERINFO);
        //name为管理员的值
        String name = "1";
        if(name=="1"){
            //根据当前登录的用户，查询该管理员管理哪个仓库
            warehouseInfoList = warehouseDao.findWarehouseInfoByWarehouseKepper(name);
            materialList = warehouseMaterialDao.findAllMaterialByWarehouseKeeper(warehouseInfoList,index,10);
        }else{
            materialList = warehouseMaterialDao.findAllMaterial(index, 10);
        }
        for (int i = 0; i < materialList.size(); i++) {
            materialList.get(i).put("warehouseID", "");
        }
        model.addAttribute("materialList", PageInfo.from(materialList));
        model.addAttribute("warehouseInfoList", warehouseInfoList);
        return "statistics/list";
    }

    /**
     * 不同仓库的物料统计列表
     * @param model
     * @param warehouseName
     * @param index
     * @return
     */
    @RequestMapping(value = "/statistics/searchlist.html", method = RequestMethod.GET)
    public String searchMaterialListByWareshoueName(Model model, @RequestParam(value = "warehouseName", defaultValue = "") String warehouseName, @RequestParam(value = "index", defaultValue = "1") int index) {

        List<WarehouseInfo>  warehouseInfoList = warehouseDao.findAll();
        String name = "1";
        if(name=="1"){
            //根据当前登录的用户，查询该管理员管理哪个仓库
            warehouseInfoList = warehouseDao.findWarehouseInfoByWarehouseKepper(name);
        }
        model.addAttribute("warehouseInfoList", warehouseInfoList);
        Page<Map> materialList = warehouseMaterialDao.findSomeMaterialByWareshoueName(warehouseName, index, 10);
        model.addAttribute("materialList", PageInfo.from(materialList));
        model.addAttribute("warehouseName",warehouseName);

        return "statistics/list";
    }

   /* public String currentUserName(ShiroHttpServletRequest request){
        CasLoginUserInfo casLoginUser = (CasLoginUserInfo)request.getSession().getAttribute(CasShiroFilter.CURRENT_LOGIN_USERINFO);
        return casLoginUser.getUserName().toString();
    }
*/
}
