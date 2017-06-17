package com.zjht.inventory.stock.manager.ui;

import com.zjht.inventory.stock.dao.CategoryDao;
import com.zjht.inventory.stock.dao.CategoryWriteDao;
import com.zjht.inventory.stock.dao.MaterialDao;
import com.zjht.inventory.stock.entity.Category;
import com.zjht.inventory.stock.entity.Materiel;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2016/10/24。
 */
@Controller
public class CategoryController {

    /**
     * 分类CODE是否存在：0.存在 1.不存在。
     */
    public static final int  CategoryCODE_EXIST = 1;
    public static final int CategoryCODE_NOTEXIST = 0;
    /**
     * 盘点CODE是否存在：1.存在 0.不存在。
     */

    public static final String NAME_EXIST = "1";
    public static final String NAME_NOTEXIST = "0";
    @ImportService
    private CategoryDao categoryDao;
    @ImportService
    private CategoryWriteDao categoryWriteDao;
    @ImportService
    private MaterialDao materialDao;

   /**
     * 物料分类树的创建CODE校验。
     CODE_NOTEXIST code不存在  code已经存在
     * @param id  id=0  是顶级树 遍历树顶 id！=0 遍历查询该树下该节点code是否重复
     * @param name 同级唯一
     * @return 结果
     */

    @RequestMapping(value = "/category/checkName.html", method = RequestMethod.POST)
    @ResponseBody
    public String checkName(Long id,String name) {
        String result = NAME_NOTEXIST;
        ArrayList<Category> categorie = categoryDao.findBySuperiorId(id);
        for (Category categorys:categorie) {
            if (categorys.getName().equals(name)) {
                result = NAME_EXIST;
            }
        }
        return result;
    }

    //树的数据
    @RequestMapping(value = "/category/list.html", method = RequestMethod.GET)
    public String list() {
        return "category/list";
    }

    /**
     * 物料分类增加树。
     * @param id 主键
     * @param name 名
     * @param code 值
     * @return null
     */

    @RequestMapping(value = "/category/add.html", method = RequestMethod.POST)
    public String add(Long id,String name,String code) {

        String encoder;
        ArrayList<Category> categorie = categoryDao.findBySuperiorId(id);
        int num = categorie.size();
        if (num < 10) {
            encoder = "0"+String.valueOf(num);
        } else {
            encoder = String.valueOf(num);
        }

        Category category = new Category();
        category.setSuperiorId(id);
        category.setName(name);
        category.setCode(code);
        category.setEncoder(encoder);
        category.setIsdel(0L);
        categoryWriteDao.addNewCategory(category);
        return "category/list";
    }

    /**
     * 物料分类 读取要更新的数据。
     * @param id 分类主键
     * @return 分类
     */
    @RequestMapping(value = "/category/updatePre.html", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePre(Long id) {
        Category category = categoryDao.findById(id);
        return category;
    }

    /**
     * 物料分类 更新已经确定要更新的数据。
     * @param id 主键
     * @param name 名
     * @param code 值
     * @param encoder code
     * @return null
     */
    @RequestMapping(value = "/category/update.html", method = RequestMethod.POST)
    public String update(Long id,String name,String code,String encoder) {

        Category category = new Category();
        category.setId(id);
        if (!StringUtils.isEmpty(name)) {
            category.setName(name);
        }
        if (!StringUtils.isEmpty(code)) {
            category.setCode(code);
        }
        if (!StringUtils.isEmpty(encoder)) {
            category.setEncoder(encoder);
        }
        categoryWriteDao.updateCategory(category);
        return "category/list";
    }

    /**
     * 物料删除 逻辑删除。
     * @param id 物料主键ID
     * @return null
     */

    @RequestMapping(value = "/category/del.html", method = RequestMethod.POST)
    @ResponseBody
    public String del(Long id) {

        List<Materiel> materials = materialDao.findByCategoryId(id);
        if (materials.size() > 0) {
            return "no";
        } else {
            categoryWriteDao.delCategory(id);
            return "yes";
        }
    }

    /**
     * 物料分类 删除检查是否存在子类。
     * @param id 物料主键ID
     * @return null
     */

    @RequestMapping(value = "/category/delPre.html", method = RequestMethod.POST)
    @ResponseBody
    public int delPre(Long id) {
        int result = CategoryCODE_NOTEXIST;
        ArrayList<Category> categorie = categoryDao.findBySuperiorId(id);

        if (categorie.size() > 0) {
            for (Category category:categorie) {
                if (category.getIsdel() == 0) {
                    result = CategoryCODE_EXIST;
                }
            }
        } else {
            result = CategoryCODE_NOTEXIST;
        }
        return result;
    }

    /**
     * 根据父亲ID查找 下一级树的数据。
     * @param superiorId 物料父类ID
     * @return 分类
     */

    @RequestMapping(value = "/category/ajaxFindById.html", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxFindById(Long superiorId) {
        Category category = categoryDao.findById(superiorId);
        return category;
    }

    /**
     * 树的数据。
     * @param superiorId 上级分类ID
     * @return 子类
     */

    @RequestMapping(value = "/category/ajaxList.html", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxList(Long superiorId) {
        ArrayList<Category> categoriess = categoryDao.findBySuperiorId(superiorId);
        ArrayList<Category> categories = new ArrayList<>();
        for (Category category:categoriess) {
            if (category.getIsdel() != 1) {
                categories.add(category);
            }
        }
        return categories;
    }

}