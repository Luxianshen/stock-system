package com.zjht.inventory.stock.manager.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.CategoryDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.entity.Category;
import com.zjht.inventory.stock.entity.Materiel;
import com.zjht.inventory.stock.entity.Metadata;
import com.zjht.inventory.stock.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
  * Created by lujiasheng on 2016/10/12。
 */

@Controller
@RequestMapping("/material")
public class MaterialController {
    /**
     * 物料CODE是否存在：0.存在 1.不存在。
     */
    public static final int  MATERIALCODE_EXIST = 0;
    public static final int MATERIALCODE_NOTEXIST = 1;


    //logger日志打印信息
    private Logger logger = LoggerFactory.getLogger(getClass());
    //引用服务
    @ImportService
    private MaterialDao materialDao;
    @ImportService
    private MaterialService materialService;
    /*@ImportService
    private ApplyService applyService;*/
    @ImportService
    private CategoryDao categoryDao;


    /**
     * 获取物料列表的方法。
     * @param index 分页代码的 首页 20显示条数
     * @return materialList 所有的物料数据（定义表的）
     */
    @RequestMapping(value = "list.html",method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "index",defaultValue = "1") int index) {
        //logger.info("w jin lai le list");
        //获取表格全部数据
        Page<Materiel> materialList = materialDao.findList(index, 20);
        model.addAttribute("materialList", PageInfo.from(materialList));
        //applyService.equals(new Object());
        return "material/list";
    }

    /**
     * ajax获取物料名字方法 主要服务于申请页面。
     * @param id（物料分类ID）
     * @return materials 物料定义表数据
     */
    @RequestMapping(value = "ajaxList.html", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxList(String id) {

        List<Materiel> materials = materialDao.findByCategoryId(Long.valueOf(id));

        return materials;
    }

    /**
     *ajax 根据传进来的code查询。
     * @param searchcode 需要搜索的code
     * @return   物料列表
     */
    @RequestMapping(value = "ajaxSearchList.html", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxSearchList(String searchcode) {

        List<Materiel> material = materialDao.findBySearchCode(searchcode);

        return  material;
    }

    /**
     *ajax 查询物料属性表的元素name 服务于申请页面。
     * @param id 物料ID
     * @return MetaData 元数据名称列表
     */
    @RequestMapping(value = "ajaxLeftList.html",method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxLeftList(Long id) {

        //查找属性名
        List<Metadata> metaData = materialDao.findMeteDataName(id);
        return metaData;
    }

    /**
     * ajax 查询某物料的所有元数据。
     * @param id 物料ID
     * @return  元数据列表
     */
    @RequestMapping(value = "ajaxGetMetadata.html",method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxGetMetadata(Long id) {

        //查找属性名
        List<Metadata> metadatas = materialDao.findMeteDataName(id);
        for (int i = 0; i < metadatas.size(); i++) {
            String name = metadatas.get(i).getName();
            if (name.contains("主键")) {
                metadatas.remove(i--);
            }
        }
        return metadatas;
    }

    /**
     * 预新增页面 辅助跳转。
     * @param model model
     * @return 返回一个物料ID
     */
    @RequestMapping(value = "addPre.html", method = RequestMethod.GET)
    public String addPre(Model model) {

        //获取物料分类主键列表
        //List<Category> categoryId = categoryDao.findTreeData();
        //model.addAttribute("CategoryId",categoryId);

        return "material/add";
    }

    /**
     *物料新增页面 处理函数。
     * @param material 传入的
     * @return null
     * @throws Exception 转换异常
     */
    @RequestMapping(value = "add.html", method = RequestMethod.POST)
    public String add(Model model,Materiel material,
                      RedirectAttributes redirectAttributes)throws Exception {

        //获取私有属性数据
        if (material.getJson() != null && material.getJson() != "") {
            //获取物料定义数据 插入表格
            //判断物料类型 按量没有私有表名 赋值为NULL
            // 按件 按照T_+物料代码+_PROPERTY 形式组装
            if (material.getType() != 1) {
                material.setMappingPrivateTable("T_" + material.getCode() + "_PROPERTY");
            } else {
                material.setMappingPrivateTable("NULL");
            }
            //获取当前时间 作为创建时间
            material.setCreateTime(new Date());
            //处理数据 json转对象数组
            ObjectMapper mapper = new ObjectMapper();
            Metadata[] metadata = mapper.readValue(material.getJson(), Metadata[].class);
            List<Metadata> metadataList = Arrays.asList(metadata);

            materialService.addNewMaterial(material, metadataList);

            //跳转页面
            Page<Materiel> materialList = materialDao.findList(1, 20);
            model.addAttribute("materialList", PageInfo.from(materialList));
        }
        //用于提示操作结果
        redirectAttributes.addFlashAttribute("message","新增物料成功!");
        return "redirect:/material/list.html";
    }

    /**
     *物料更新 读取旧数据业务。
     * @param materialId 要修改的物料ID
     * @return 物料 元数据列表 分类
     */
    @RequestMapping(value = "updatePre.html", method = RequestMethod.GET)
    public String updatePre(Model model , Long materialId) {

        //先获取要修改的数据
        Materiel material = materialDao.findById(materialId);
        List<Metadata> metadatas = materialDao.findMeteData(materialId);
        List<Category> categoryId = categoryDao.findTreeData();
        List<Metadata> metadatass =new ArrayList<>();
        for ( Metadata metadata:metadatas) {
            metadata.setLength(metadata.getLength());
            metadatass.add(metadata);
        }
        //获取物料分类 会数组越界
        for (Category category:categoryId) {
            if (category.getId() == material.getMaterielCategoryId()) {
                model.addAttribute("Category",category);
            }
        }
        //显示到前台
        model.addAttribute("material",material);
        model.addAttribute("metadatas",metadatas);

        return "material/update";
    }

    /**
     * 物料更新 持久化业务。
     * @param material 要修改的物料 修改的元数据信息 在material.getJson
     * @return 结果码
     * @throws IOException 抛出转换异常
     */
    @RequestMapping(value = "update.html", method = RequestMethod.POST)
    public String update(Model model,Materiel material,RedirectAttributes redirectAttributes)
            throws IOException {

        //检验数据
        //如果有元数据 就进if 更新旧物料数据
        if (material.getJson() != null && material.getJson() != "") {
            //处理数据 json转对象数组
            ObjectMapper mapper = new ObjectMapper();
            Metadata[] jsonList = mapper.readValue(material.getJson(), Metadata[].class);
            List<Metadata> dataList = Arrays.asList(jsonList);
            //提交到service
            materialService.updateOldMaterial(material,dataList);
        }

        //获取最新 已经修改过的数据
        Materiel material1 = materialDao.findById(material.getId());
        List<Category> categoryId = categoryDao.findTreeData();
        for (Category category:categoryId) {
            if (category.getId() == material1.getMaterielCategoryId()) {
                model.addAttribute("Category",category);
            }
        }
        List<Metadata> metadatas = materialDao.findMeteData(material.getId());
        //显示到前台
        model.addAttribute("material",material1);
        model.addAttribute("metadatas",metadatas);

        //处理结果
        //用于提示操作结果
        redirectAttributes.addFlashAttribute("message","更新物料成功!");
        return "redirect:/material/list.html";
    }

    /**
     * 根据物料ID 查询 某一个物料的详细信息。
     * @param materialId 物料ID
     * @return    元数据列表
     */
    @RequestMapping(value = "detail.html", method = RequestMethod.GET)
    public String detail(Model model,@RequestParam("materialId") Long materialId) {

        //查找物料定义表
        Materiel material = materialDao.findById(materialId);
        //查找私有属性表
        List<Metadata> metaData = materialDao.findMeteDataName(materialId);
        //传数值到界面

        model.addAttribute("material",material);
        model.addAttribute("MetaData",metaData);
        return "material/detail";
    }

    /**
     *ajax 用于校验新增的元数据code 是否已经存在。
     * @param code 元数据code
     * @param id 物料ID
     * @return 1代表没有相同code 0表示 已经有了该code
     */
    @RequestMapping(value = "findCode.html", method = RequestMethod.POST)
    @ResponseBody
    public int findcode(@RequestParam("code") String code,@RequestParam("id") Long id) {

        int result = MATERIALCODE_NOTEXIST;
        List<Metadata> metadatas = materialDao.findMeteData(id);
        if (metadatas.size() > 0 && metadatas != null) {
            for (Metadata meta:metadatas) {
                if (code.equals(meta.getCode())) {
                    result = MATERIALCODE_EXIST;
                }
            }
        }

        return result;
    }

    /**
     *ajax 用于校验新增的物料code 是否已经存在。
     * @param code 物料code
     * @return  1代表没有相同code 0表示 已经有了该code
     */
    @RequestMapping(value = "findMaterielCode.html", method = RequestMethod.POST)
    @ResponseBody
    public int findMaterielCode(@RequestParam("code") String code) {

        int result = MATERIALCODE_NOTEXIST;
        List<Materiel> metadatas = materialDao.findMaterielCode();
        if (metadatas.size() > 0 && metadatas != null) {
            for (Materiel meta:metadatas) {
                if (code.equals(meta.getCode())) {
                    result = MATERIALCODE_EXIST;
                }
            }
        }

        return result;
    }

    /**
     * 物料列表 搜索。
     * @param material 包括code、name、类型
     * @param index 分页数
     * @return 物料列表
     */
    @RequestMapping(value = "search.html",method = RequestMethod.GET)
    public String search(Model model, Materiel material,
                         @RequestParam(value = "index",defaultValue = "1") int index) {

        //获取表格全部数据
        //回显数据
        model.addAttribute("materialcode",material.getCode());
        model.addAttribute("materialname",material.getName());
        model.addAttribute("materialtype",material.getType());
        //如果输入信息不为空
        if (material != null) {
            material.setCode(material.getCode().trim());
            material.setName(material.getName().trim());
            Page<Materiel> materialList = materialDao.searchList(index,10,material);
            model.addAttribute("materialList", PageInfo.from(materialList));
        } else { //输入数据为空 select *
            Page<Materiel> materialList = materialDao.findList(index, 10);
            model.addAttribute("materialList", PageInfo.from(materialList));
        }

        //用于提示操作结果
        return "material/list";
    }

}
