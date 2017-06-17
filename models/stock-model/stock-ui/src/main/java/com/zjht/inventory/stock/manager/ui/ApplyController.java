package com.zjht.inventory.stock.manager.ui;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.commons.utils.StatusUtil;
import com.zjht.inventory.stock.dao.ApplyDao;
import com.zjht.inventory.stock.dao.InventoryDetailedDao;
import com.zjht.inventory.stock.dao.InventoryDetailedWriteDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.dao.ScheduingDao;
import com.zjht.inventory.stock.dao.WarehouseMaterialDao;
import com.zjht.inventory.stock.entity.Apply;
import com.zjht.inventory.stock.entity.ApplyList;
import com.zjht.inventory.stock.entity.MaterialInfoVO;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.service.ApplyService;
import com.zjht.inventory.stock.utils.InOutUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jason on 2016/10/12.
 */
@Controller
public class ApplyController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ImportService
    ApplyDao applyDao;
    @ImportService
    ApplyService applyService;
    @ImportService
    MaterialDao materialDao;
    @ImportService
    private InventoryDetailedDao inventoryDetailedDao;
    @ImportService
    private InventoryDetailedWriteDao inventoryDetailedWriteDao;
    @ImportService
    private WarehouseMaterialDao warehouseMaterialDao;
    @ImportService
    private ScheduingDao scheduingDao;


    @RequestMapping(value = "/apply/list", method = RequestMethod.GET)
    public String list(Model model, Apply apply, String startTime, String endTime,
                       @RequestParam(value = "index",defaultValue = "1") int index,
                       @RequestParam(value = "size",defaultValue = "20") int size) {

        String warehouse ="1";
        if(warehouse!=null){
            apply.setWarehouseBelong(63L);
        }else{
            return "redirect:/error/noWarehouse.html";
        }
        if (apply.getOrderId() != null){
            apply.setOrderId(apply.getOrderId().trim());
        }
        Page<Apply> applies = applyDao.findByPage(apply.getOrderId(),63L, apply.getApplyType(),
                apply.getStatus(), apply.getHandleResult(), startTime, endTime, index, size);


        for(Apply a : applies){
            a.setHandleResultName(StatusUtil.getHandleResultName(a.getHandleResult()!=null?a.getHandleResult():StatusUtil.HANDLE_RESULT_AWAIT));
            a.setApplyTypeName(StatusUtil.all.get(a.getApplyType()!=null?a.getApplyType():-1l));
        }

        model.addAttribute("applies", PageInfo.from(applies));
        model.addAttribute("apply", apply);
        model.addAttribute("types", InOutUtils.keyToStringMap(StatusUtil.all));
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("STOCK_LOCK", StatusUtil.STOCK_LOCK);

        return "apply/list";
    }

    @RequestMapping(value = "/apply/add", method = RequestMethod.GET)
    public String add(Model model, Long applyType) {
        model.addAttribute("applyType",applyType);
        return "apply/add";
    }

    @RequestMapping(value = "/apply/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addProcessor(Model model, @RequestBody Apply apply) {
        String user = "casso";
        String warehouse = "63";
        if(warehouse!=null){
            apply.setWarehouseBelong(63L);
        }else{
            return "你没有权限对仓库操作!";
        }
        //设置申请人用户名
        apply.setCreateBy(user);
        //设置创建时间
        apply.setCreateTime(new Date());
        //设置系统订单编号
        apply.setOrderId((new Random().nextInt(9))+""+(System.currentTimeMillis()));
        //applyService.addApply();
        //String str = InOutUtils.checkTheInventory(apply, generator, inventoryDetailedDao, materialDao, warehouseMaterialDao, lockDao,warehouseMaterialService);
        /*if(!StringUtils.isEmpty(str)){
            return str;
        }*/
        try {
            applyService.createApply(apply);
        } catch (Exception e) {
            String message = "添加申请出错 ";
            logger.error(message,e);
            return message;
        }
        return true;
    }

    @RequestMapping(value = "/apply/convert", method = RequestMethod.POST)
    public String convertProcessor(Model model, Long applyId, Long convertType, String remark,
                                   RedirectAttributes redirectAttributes) {
        String user = "admin";
        String redirect = "redirect:/apply/list.html";
        if(user==null || StringUtils.isEmpty(user)){
            redirectAttributes.addFlashAttribute("errorMessage","请先登陆!");
            return redirect;
        }
        Apply apply = applyDao.findById(applyId);
        if(apply==null){
            redirectAttributes.addFlashAttribute("errorMessage","未找到锁定申请单!");
            return redirect;
        }
        boolean isOut = StatusUtil.isInOut(StatusUtil.outs, convertType);
        if(!isOut){
            redirectAttributes.addFlashAttribute("errorMessage","锁定单只能转变为出库类申请单!");
            return redirect;
        }
        ArrayList<ApplyList> applyLists = apply.getApplyLists();
        if(applyLists==null || applyLists.size()<=0){
            redirectAttributes.addFlashAttribute("errorMessage","该申请单没有锁定任何物料!");
            return redirect;
        }
        try {
            applyService.convertApply(apply,convertType,remark);
        } catch (Exception e) {
            logger.error("转变申请单出错 ",e);
            redirectAttributes.addFlashAttribute("errorMessage",e.getLocalizedMessage());
            return redirect;
        }
        redirectAttributes.addFlashAttribute("message","转变成功!");
        return redirect;
    }

    @RequestMapping(value = "/apply/nullify", method = RequestMethod.GET)
    public Object nullifyProcessor(Model model, Long applyId, RedirectAttributes redirectAttributes) {
        String user = "admin";
        Apply apply = applyDao.findById(applyId);
        if(apply==null){
            redirectAttributes.addFlashAttribute("errorMessage", "申请单不存在!");
            return "redirect:/apply/list.html";
        }else if(apply.getHandleResult()!=StatusUtil.HANDLE_RESULT_AWAIT){
            String msg = "该申请单已处理!";
            redirectAttributes.addFlashAttribute("errorMessage",msg);
            return "redirect:/apply/list.html";
        }
        try {
            applyService.nullify(apply.getId(), apply.getApplyType(), user);
            redirectAttributes.addFlashAttribute("message","已作废!");
        } catch (Exception e) {
            String msg = "处理申请单出错!";
            logger.error(msg,e);
            redirectAttributes.addFlashAttribute("errorMessage",msg);
        }
        return "redirect:/apply/list.html";
    }

    @RequestMapping(value = "/apply/details", method = RequestMethod.GET)
    public String details(Model model, Long applyId) {
        Apply apply = applyDao.findById(applyId);

        apply.setHandleResultName(StatusUtil.getHandleResultName(apply.getHandleResult()!=null?apply.getHandleResult():StatusUtil.HANDLE_RESULT_AWAIT));
        apply.setApplyTypeName(StatusUtil.all.get(apply.getApplyType()!=null?apply.getApplyType():-1l));

        model.addAttribute("apply",apply);
        return "apply/details";
    }

    @RequestMapping(value = "/apply/unlock", method = RequestMethod.GET)
    public String unlock(Model model, Long applyId, RedirectAttributes redirectAttributes) {
        //获取登陆用户
        String user = "admin";
        if(user==null) return "ERROR:请先登录!";
        String listPage = "redirect:/apply/list.html";
        if(StringUtils.isEmpty(applyId)){
            return listPage;
        }
        try {
            applyService.unlock(applyId,user,null);
        } catch (Exception e) {
            String message = e.getMessage();
            logger.error(message,e);
            redirectAttributes.addFlashAttribute("errorMessage",message);
            return listPage;
        }
        redirectAttributes.addFlashAttribute("message","解锁完成!");
        return listPage;
    }

    @RequestMapping(value = "/apply/handle", method = RequestMethod.GET)
    public String handle(Model model, Long applyId, RedirectAttributes redirectAttributes) {
        if(StringUtils.isEmpty(applyId)){
            redirectAttributes.addFlashAttribute("errorMessage","申请编号为空!");
            return "redirect:/apply/list.html";
        }
        Apply apply = applyDao.findById(applyId);
        if(apply!=null && apply.getHandleResult()!=1){
            redirectAttributes.addFlashAttribute("errorMessage","申请单已处理!");
            return "redirect:/apply/list.html";
        }
        model.addAttribute("apply",apply);
        Long applyType = apply.getApplyType();
        if(applyType ==StatusUtil.STOCK_IN_PURCHASE ||
                applyType == StatusUtil.STOCK_IN_CHECK ||
                applyType == StatusUtil.STOCK_IN_EXCHANGE){
            ArrayList<Long> list = new ArrayList<>();
            ArrayList<ApplyList> applyLists = apply.getApplyLists();
            for (ApplyList al : applyLists){
                Long materielId = al.getMaterielId();
                List<Metadata> metadatas = materialDao.findMeteData(materielId);
                if (metadatas==null || metadatas.size()<=0){
                    list.add(materielId);
                }
            }
            model.addAttribute("noProperties",list);
            return "apply/handle-purchase";
        }
        return "apply/handle";
    }

    @RequestMapping(value = "/apply/handle", method = RequestMethod.POST)
    public Object handleProcessor(Model model, MaterialInfoVO vo) {
        //获取登陆用户
        String user = "casso";
        if(user==null) return "ERROR:请先登录!";
        //Apply apply = applyDao.findById(vo.getApplyId());
        try {
            Map<Long,List<String>> link = new LinkedHashMap<>();
            ArrayList<Map<String, String>> maps = vo.getMaps();
            List<String> assetsAll = new ArrayList<>();
            if(maps==null || maps.size()==0){
                return "该订单没有相关清单数据,无法处理!";
            }
            for (Map<String, String> map : maps) {
                List<String> assetsNos = new ArrayList<>();
                Long listId = -1l;
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    String item = next.getKey();
                    String value = next.getValue();
                    if ("assetsNos".equals(item)) {
                        try {
                            assetsNos = Arrays.asList(value.split(","));
                            assetsAll.addAll(assetsNos);
                        } catch (Exception e) {
                            return "收集 "+value+" 资产编号出错!";
                        }
                    }else if ("listId".equals(item)) {
                        try {
                            listId = Long.valueOf(value);
                        } catch (NumberFormatException e) {
                            return value+" 清单编号不符合Long类型!";
                        }
                    }
                }
                //添加Service处理数据
                link.put(listId,assetsNos);
            }
            //判断是否存在重复资产编号
            HashSet<String> set = new HashSet<>();
            set.addAll(assetsAll);
            if(assetsAll.contains("")){return "资产编号存在空值 !";}
            if(set.size()!=assetsAll.size()){return "资产编号存在重复!";}
            Long id = Long.valueOf(vo.getApplyId());
            applyService.changeStatus(id);
            //applyService.changeStatus(link.get(""));
            /*try {
                scheduingService.inOut(apply, link, user.getUserName(),vo.getDrawingPerson(),vo.getHandleRemark());
            } catch (NullPointerException e) {
                return "申请单"+vo.getApplyId()+"信息异常!";
            } catch (Exception e){
                return e.getLocalizedMessage();
            }*/
        }catch (Exception e){
            return "服务器遇到致命错误,请联系管理员";
        }
        return "redirect:/apply/list.html";
    }
}
