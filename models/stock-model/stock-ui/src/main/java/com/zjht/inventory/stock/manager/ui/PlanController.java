package com.zjht.inventory.stock.manager.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.*;
import com.zjht.inventory.stock.entity.*;
import com.zjht.inventory.stock.service.CheckPlanService;
import com.zjht.inventory.stock.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jpower on 2016/10/19.
 */

@Controller
@RequestMapping("/plan")
public class PlanController {

	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

	@ImportService
	private WarehouseInfoDao warehouseDao;

	@ImportService
	private MaterialDao materialDao;

	@ImportService
	private CheckPlanDao checkPlanDao;
	
	@ImportService
	private CheckResultDao checkResultDao ; 
	
	@ImportService 
	private WarehouseMaterialDao warehouseMaterialDao ; 

	@ImportService
	private CheckPlanService checkPlanService;

	/**
	 * 查询盘点计划列表
	 * @param checkPlanDto 
	 * @param index 
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, CheckPlanDto checkPlanDto,
			@RequestParam(value = "index", defaultValue = "1") int index) {
		logger.info("盘点列表页面-------------------------");
		Page<CheckPlanDto> checkPlanList = checkPlanDao.findCheckPlanList(checkPlanDto, index, 8);
		model.addAttribute("checkPlanList", PageInfo.from(checkPlanList));
		return "plan/list";
	}
	
	/**
	 * 按条件查询盘点计划记录
	 * @param planName 
	 * @param name 
	 * @param checkMaterial
	 * @param checkProportion
	 * @param index 
	 * */
	@RequestMapping(value = "/conditionList", method = RequestMethod.POST )
	public String conditionList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String planName, @RequestParam  String name, @RequestParam String checkMaterial,
			@RequestParam String checkProportion, @RequestParam(value = "index", defaultValue = "1") int index) {
		logger.info("盘点列表页面-------------------------");
		CheckPlanDto checkPlanDto = new CheckPlanDto();
		if (StringUtils.isNotEmpty(planName)) {
			model.addAttribute("planName", planName);
			checkPlanDto.setPlanName(planName);
		}
		if (StringUtils.isNotEmpty(name)) {
			model.addAttribute("name", name);
			checkPlanDto.setName(name);
		}
		if (StringUtils.isNotEmpty(checkMaterial)) {
			model.addAttribute("checkMaterial", checkMaterial);
			checkPlanDto.setCheckMaterial(checkMaterial);
		}
		if (StringUtils.isNotEmpty(checkProportion)) {
			model.addAttribute("checkProportion", checkProportion);
			checkPlanDto.setCheckProportion(Long.parseLong(checkProportion));
		}
		Page<CheckPlanDto> checkPlanList = checkPlanDao.findCheckPlanList(checkPlanDto, index, 8);
		model.addAttribute("checkPlanList", PageInfo.from(checkPlanList));
		return "plan/list";
	}
	
	/**
	 * 进入添加盘点计划页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// TODO 临时固定管理员
		String whKepper = "ZJHT12306";
		List<WarehouseInfo> warehouseInfos = warehouseDao.findWarehouseInfoByWarehouseKepper(whKepper);
		model.addAttribute("warehouseInfos", warehouseInfos);
		System.out.println("进入添加盘点计划页面===============");
		return "plan/add";
	}

	/**
	 * 获取仓库
	 * 
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getWarehouse", method = RequestMethod.GET)
	public void searchWarehouse(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws JsonProcessingException {
		// TODO 临时固定管理员
		String whKepper = "ZJHT12306";

		ObjectMapper ajaxResult = new ObjectMapper();
		List<WarehouseInfo> warehouseInfos = warehouseDao.findWarehouseInfoByWarehouseKepper(whKepper);
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> result = new HashMap<String, Object>();
		if (warehouseInfos != null && warehouseInfos.size() > 0) {
			for (WarehouseInfo warehouseInfo : warehouseInfos) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", warehouseInfo.getId());
				data.put("name", warehouseInfo.getName());
				datas.add(data);
			}
			result.put("data", datas);
		} else {
			result.put("data", "");
		}
		result.put("status", 1);
		String jsonData = ajaxResult.writeValueAsString(result);
		System.out.println("仓库数据：====" + jsonData);
		ResponseUtils.renderJson(response, jsonData);
	}

	/**
	 * 获取物料
	 * 
	 * @throws JsonProcessingException
	 */
	@ResponseBody
	@RequestMapping(value = "/getMateriel", method = { RequestMethod.POST, RequestMethod.GET })
	public void searchWarehouse(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			String warehouseId) throws JsonProcessingException {
		ObjectMapper ajaxResult = new ObjectMapper();
		List<Materiel> materials = materialDao.fineMaterialByWarehouseId(Long.parseLong(warehouseId));
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> result = new HashMap<String, Object>();
		if (materials != null && materials.size() > 0) {
			for (Materiel material : materials) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", material.getId());
				data.put("name", material.getName());
				datas.add(data);
			}
			result.put("data", datas);
		} else {
			result.put("data", "");
		}
		result.put("status", 1);
		String jsonData = ajaxResult.writeValueAsString(result);
		ResponseUtils.renderJson(response, jsonData);
	}

	/**
	 * 保存盘点计划
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String planName, @RequestParam Long checkWarehouse, @RequestParam Long checkProportion,
			@RequestParam String checkMaterial) {
		logger.info("添加盘点计划记录---------");
		ObjectMapper ajaxResult = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		Long shouldCheckNum = 0L;

		CheckPlan checkPlan = new CheckPlan();
		checkPlan.setCreateTime(new Date());// 创建时间
		checkPlan.setPlanName(planName);// 盘点名称
		checkPlan.setCheckWarehouse(checkWarehouse);// 盘点仓库
		checkPlan.setCheckProportion(checkProportion);// 盘点比例
		if ("all".equals(checkMaterial)) {// 全盘
			checkPlan.setCheckMaterial("全盘");
			// 1、查找仓库的物料 2、循环计算该盘点数量
			List<WarehouseMaterial> warehouseMaterials = warehouseMaterialDao.findWarehouseMaterialByWarehouseId(checkWarehouse);
			if (warehouseMaterials != null && warehouseMaterials.size() > 0) {
				for (int i = 0; i < warehouseMaterials.size(); i++) {
					shouldCheckNum += ((long) warehouseMaterials.get(i).getInventoryCount() * checkProportion) / 100L;
				}
				checkPlan.setShouldCheckNum(shouldCheckNum);
				result.put("status", 1);
				result.put("toUrl", "list.html");
			} else {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
		} else {
			// 1、查找物料名称 2、根据仓库ID与物料ID查找物料数量 3、计算该盘点数量
			Materiel material = materialDao.findById(Long.parseLong(checkMaterial));
			if (material == null) {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
			checkPlan.setCheckMaterial(material.getName());
			WarehouseMaterial warehouseMaterial = warehouseMaterialDao.findByWarehouseIdAndMaterialId(checkWarehouse, Long.parseLong(checkMaterial));
			if (warehouseMaterial == null) {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
			shouldCheckNum = ((long) warehouseMaterial.getInventoryCount() * checkProportion) / 100L;
			checkPlan.setShouldCheckNum(shouldCheckNum);
			result.put("status", 1);
			result.put("toUrl", "list.html");
		}
		checkPlanService.createCheckPlan(checkPlan);
		String jsonData;
		try {
			jsonData = ajaxResult.writeValueAsString(result);
			ResponseUtils.renderJson(response, jsonData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 删除盘点计划
	 * @param id
	 * */
	@ResponseBody
	@RequestMapping(value = "/planDelete", method = RequestMethod.POST)
	public void planDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model, String id)
			throws JsonProcessingException {
		ObjectMapper ajaxResult = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		checkPlanService.deletePlan(Long.parseLong(id));
		result.put("status", 1);
		String jsonData = ajaxResult.writeValueAsString(result);
		ResponseUtils.renderJson(response, jsonData);
	}
	
	/**
	 * 检查盘点计划是否已有相关的盘点记录，有则不允许修改盘点计划，并提示用户
	 * 
	 * @param id
	 */
	@ResponseBody
	@RequestMapping(value = "/checkResult", method = RequestMethod.POST)
	public void checkResult(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String id) {
		ObjectMapper ajaxResult = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		CheckResult checkResult = checkResultDao.findCheckResultByPlanId(id);
		if(checkResult==null){
			result.put("status", 1);
			result.put("msg", "未存在盘点记录，请先导入！");
		}
		else{
			result.put("status", 0);
			result.put("msg", "已存在盘点记录，不能操作该功能！");
		}
		String jsonData;
		try {
			jsonData = ajaxResult.writeValueAsString(result);
			ResponseUtils.renderJson(response, jsonData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询数据，跳转到修改页面
	 * @param id
	 * */
	@RequestMapping(value = "/toUpdate", method = RequestMethod.GET)
	public String toUpdate(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String id) {
		CheckPlan checkPlan = checkPlanDao.findCheckPlanByID(id);
		WarehouseInfo warehouseInfo = warehouseDao.findWarehouseById(checkPlan.getCheckWarehouse());
		if (warehouseInfo == null) {
			logger.info("warehouseInfo-------找不到记录，发生异常");
		}
		List<Materiel> materials = materialDao.fineMaterialByWarehouseId(checkPlan.getCheckWarehouse());

		model.addAttribute("id", checkPlan.getId());
		model.addAttribute("planName", checkPlan.getPlanName());
		model.addAttribute("warehouseId", checkPlan.getCheckWarehouse());
		model.addAttribute("checkWarehouse", warehouseInfo.getName());
		model.addAttribute("checkProportion", checkPlan.getCheckProportion());
		model.addAttribute("materials", materials);
		if ("全盘".equals(checkPlan.getCheckMaterial())) {
			model.addAttribute("checkMaterial", "all");
		} else {
			if (materials.size() > 0 && materials != null) {
				for (int i = 0; i < materials.size(); i++) {
					if (checkPlan.getCheckMaterial().equals(materials.get(i).getName())) {
						model.addAttribute("checkMaterial", materials.get(i).getId());
						break;
					}
				}
			} else {
				model.addAttribute("checkMaterial", "");
			}
		}
		return "plan/update";
	}
	
	/**
	 * 更新盘点计划
	 * @param id
	 * @param checkProportion
	 * @param checkMaterial
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateCheckplan", method = RequestMethod.POST)
	public void updateCheckplan(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String id, @RequestParam Long checkProportion, @RequestParam String checkMaterial) {
		ObjectMapper ajaxResult = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		Long shouldCheckNum = 0L;
		
		CheckPlan checkPlan = checkPlanDao.findCheckPlanByID(id);
		if(checkPlan==null){
			result.put("status", -1);
			result.put("msg", "数据异常，请稍后再试");
		}
		checkPlan.setCheckProportion(checkProportion);// 修改盘点比例
		checkPlan.setCreateTime(new Date());// 修改创建时间
		if ("all".equals(checkMaterial)) {// 全盘
			checkPlan.setCheckMaterial("全盘");
			// 1、查找仓库的物料 2、循环计算该盘点数量
			List<WarehouseMaterial> warehouseMaterials = warehouseMaterialDao.findWarehouseMaterialByWarehouseId(checkPlan.getCheckWarehouse());
			if (warehouseMaterials != null && warehouseMaterials.size() > 0) {
				for (int i = 0; i < warehouseMaterials.size(); i++) {
					shouldCheckNum += ((long) warehouseMaterials.get(i).getInventoryCount() * checkProportion) / 100L;
				}
				checkPlan.setShouldCheckNum(shouldCheckNum);
				result.put("status", 1);
				result.put("toUrl", "list.html");
			} else {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
		} else {
			// 1、查找物料名称 2、根据仓库ID与物料ID查找物料数量 3、计算该盘点数量
			Materiel material = materialDao.findById(Long.parseLong(checkMaterial));
			if (material == null) {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
			checkPlan.setCheckMaterial(material.getName());
			WarehouseMaterial warehouseMaterial = warehouseMaterialDao.findByWarehouseIdAndMaterialId(checkPlan.getCheckWarehouse(), Long.parseLong(checkMaterial));
			if (warehouseMaterial == null) {
				result.put("status", -1);
				result.put("msg", "数据异常，请稍后再试");
			}
			shouldCheckNum = ((long) warehouseMaterial.getInventoryCount() * checkProportion) / 100L;
			checkPlan.setShouldCheckNum(shouldCheckNum);
			result.put("status", 1);
			result.put("toUrl", "list.html");
		}
		checkPlanService.updateCheckPlan(checkPlan);
		String jsonData;
		try {
			jsonData = ajaxResult.writeValueAsString(result);
			ResponseUtils.renderJson(response, jsonData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	
	/********盘点计划*******************************************************************************/
	
	/**
	 * 进入盘点记录页面
	 * 
	 * @param
	 * @param index
	 */
	@RequestMapping(value = "/recordlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String recordlist(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(value = "index", defaultValue = "1") int index) {
		logger.info("盘点记录页面--------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("planName", null);
		map.put("checkMaterial", null);
		map.put("checkProportion", null);
		map.put("checkStatus", null);
		Page<PlanRecordDto> planRecordList = checkPlanDao.findPlanRecordList(map, index, 8);
		model.addAttribute("planRecordList", PageInfo.from(planRecordList));
		return "plan/recordlist";
	}
	
	/**
	 * 按条件查询盘点记录
	 * 
	 * @param planName
	 * @param checkMaterial
	 * @param checkProportion
	 * @param checkStatus
	 * @param index
	 */
	@RequestMapping(value = "/conditionRecord", method = RequestMethod.POST)
	public String conditionRecord(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String planName, @RequestParam String checkMaterial, @RequestParam String checkProportion,
			@RequestParam String checkStatus, @RequestParam(value = "index", defaultValue = "1") int index) {
		logger.info("盘点记录列表页面-------------------------");
		Map<String, Object> map = new HashMap<String, Object>();
		model.addAttribute("planName", planName);
		map.put("planName", planName);
		model.addAttribute("checkMaterial", checkMaterial);
		map.put("checkMaterial", checkMaterial);
		model.addAttribute("checkProportion", checkProportion);
		map.put("checkProportion", checkProportion);
		model.addAttribute("checkStatus", checkStatus);
		map.put("checkStatus", checkStatus);
		Page<PlanRecordDto> planRecordList = checkPlanDao.findPlanRecordList(map, index, 8);
		model.addAttribute("planRecordList", PageInfo.from(planRecordList));
		return "plan/recordlist";
	}
	
	/**
	 * 跳转到上传盘点记录页面
	 * 
	 * @param planId
	 * @return
	 */
	@RequestMapping(value = "/toUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String toUpload(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String planId) {
		//查询盘点计划
		CheckPlan checkPlan = checkPlanDao.findCheckPlanByID(planId);
		//查询盘点仓库
		WarehouseInfo warehouseInfo = warehouseDao.findWarehouseById(checkPlan.getCheckWarehouse());

		model.addAttribute("checkPlan", checkPlan);
		model.addAttribute("warehouseInfo", warehouseInfo);
		return "plan/upload";
	}
	
	//TODO ------------------------------------------------------------
	/**
	 * 预览上传数据
	 * 
	 * @param planId
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/preview", method = RequestMethod.POST)
	public String preview(HttpServletRequest request, HttpServletResponse response, ModelMap model,@RequestParam String planId,
			@RequestParam MultipartFile fileName, @RequestParam String fileExt){
		List<Map<String, Object>> getData = null;
		if (".xls".equals(fileExt)) {
			getData = getXLSData(fileName, 1);
		} else if (".xlsx".equals(fileExt)) {
			getData = getXLSXData(fileName, 1);
		}
		// 获取资产编号列表
		List<String> assetList = new ArrayList<String>();
		List<String> returnList = new ArrayList<String>();
		for (int i = 0; i < getData.size(); i++) {
			assetList.add(getData.get(i).get("assetsNo").toString().trim());
			returnList.add(getData.get(i).get("assetsNo").toString().trim());
		}
		// 根据订单号与资产列表记录获取数据库中存在的记录
		List<String> existList = checkPlanDao.findExistAssetNo(planId, assetList);
		for (int i = 0; i < existList.size(); i++) {
			assetList.remove(existList.get(i));
		}
		PlanDifferDto existDetail = checkPlanDao.findExistDetail(planId, existList);
		List<WarehouseInventory> previewList = existDetail.getWarehouseInventory();
		if (assetList.size() != 0 && assetList != null) {
			for (int i = 0; i < assetList.size(); i++) {
				WarehouseInventory data = new WarehouseInventory();
				data.setRemark("资产编号" + assetList.get(i) + "不存在");
				data.setCheckStatus("不在系统");
				InventoryDetailed invenDetl = new InventoryDetailed();
				invenDetl.setAssetsNo("资产编号不存在");
				data.setInventoryDetailed(invenDetl);
				previewList.add(data);
			}
		}
		model.addAttribute("previewList", previewList);
		model.addAttribute("planId", planId);
		model.addAttribute("excelData", returnList.toString());
		return "plan/preview";
	}
	
	@ResponseBody
	@RequestMapping(value = "/savedetails", method = RequestMethod.POST)
	public void savedetails(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam String planId, @RequestParam String excelData) {
		System.out.println(excelData);
		
	}
	
	
	
	
	
	//TODO ------------------------------------------------------------
	/**
	 * 解析上传的excel数据，xls格式
	 * 
	 * @param file
	 * @param ignoreRows
	 * @return
	 */
	private List<Map<String, Object>> getXLSData(MultipartFile file, int ignoreRows) {
		List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
		try {
			POIFSFileSystem poiFile = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook workbook = new HSSFWorkbook(poiFile);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Map<String, Object> data = new HashMap<String, Object>();
				HSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				for (int columnIndex = 0; columnIndex <= 2; columnIndex++) {
					String assetsNo = row.getCell(1).toString().trim();
					data.put("assetsNo",assetsNo.substring(0, assetsNo.indexOf(".")) );
					data.put("checkStatus", row.getCell(2).toString().trim());
				}
				resultData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultData;
	}

	/**
	 * 解析上传的excel数据，xlsx格式
	 * 
	 * @param file
	 * @param ignoreRows
	 * @return
	 */
	private List<Map<String, Object>> getXLSXData(MultipartFile file, int ignoreRows) {
		List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int rowIndex = ignoreRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Map<String, Object> data = new HashMap<String, Object>();
				XSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				for (int columnIndex = 0; columnIndex <= 2; columnIndex++) {
					String assetsNo = row.getCell(1).toString().trim();
					data.put("assetsNo",assetsNo.substring(0, assetsNo.indexOf(".")) );
					data.put("checkStatus", row.getCell(2).toString().trim());
				}
				resultData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultData;
	}
	
}
