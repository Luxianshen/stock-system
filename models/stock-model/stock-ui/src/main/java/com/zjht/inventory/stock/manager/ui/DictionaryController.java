package com.zjht.inventory.stock.manager.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.DictionaryDao;
import com.zjht.inventory.stock.entity.Dictionary;
import com.zjht.inventory.stock.service.DictionaryService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Created by lujiasheng on 2016/10/31。
 */
@Controller
public class DictionaryController {

    //调用服务
    @ImportService
    DictionaryDao dictionaryDao;
    @ImportService
    DictionaryService dictionaryService;

    /**
     * 查找是关联的数据字典信息 服务于申请入库。
     * @param code 字典code
     * @return 字典
     */
    @RequestMapping(value = "/dictionary/rightData.html" , method = RequestMethod.POST)
    @ResponseBody
    public Object list(String code) {

        List<Dictionary> dictionary = dictionaryDao.findDataByCode(code);

        return dictionary;
    }

    /**
     * 查询字典列表。
     * @param model Model
     * @param index 主页数
     * @return  null
     */
    @RequestMapping(value = "/dictionary/list.html" , method = RequestMethod.GET)
    public String list(Model model,@RequestParam(value = "index",defaultValue = "1") int index) {

        Page<Dictionary> dictionaryList = dictionaryDao.findAllDictionary(index,10);
        model.addAttribute("dictionaryList", PageInfo.from(dictionaryList));
        return "dictionary/list";
    }

    /**
     *查找所有的 字典code。
     * @return 字典列表
     */
    @RequestMapping(value = "/dictionary/listCode.html" , method = RequestMethod.POST)
    @ResponseBody
    public Object dictionaryCode() {

        List<Dictionary> listCode = dictionaryDao.findDictionaryCode();
        return listCode;
    }

    /**
     * 根据字典代码，查询字典的信息。
     * @param index 主页数
     * @param dictionaryCode 字典代码
     * @return 字典选项列表
     */
    @RequestMapping(value = "/dictionary/updatePre.html" , method = RequestMethod.POST)
    @ResponseBody
    public Object updatePre(@RequestParam(value = "index",defaultValue = "1")
                                        int index,String dictionaryCode) {

        Page<Dictionary> dictionaryItemList =
                dictionaryDao.findAllDictionaryItem(index,8,dictionaryCode);
        return dictionaryItemList;
    }

    /**
     * 数据字典 更新或者新增 方法。
     *  修改的数据
     * @param index  主页数
     * @param dictionaryCode 字典CODE
     * @param redirectAttributes 重定向
     * @return null
     * @throws IOException 转换异常
     */
    @RequestMapping(value = "/dictionary/updateOradd.html" , method = RequestMethod.POST)
    public String update(Model model,@RequestParam(value = "index",defaultValue = "1")
                         int index,Dictionary dictionaryCode, RedirectAttributes redirectAttributes)
                            throws IOException {

        //判断数据是否有效
        if (StringUtils.isEmpty(dictionaryCode.getJson())) {
            if (StringUtils.isEmpty(dictionaryCode.getCode())) {
                dictionaryService.changeName(dictionaryCode.getId(),dictionaryCode.getName());
            } else {
                //新增字典 没有选项
                dictionaryService.addDictionary(dictionaryCode);
            }
        } else {
            //数据处理
            ObjectMapper mapper = new ObjectMapper();
            Dictionary[] jsonList = mapper.readValue(dictionaryCode.getJson(), Dictionary[].class);
            List<Dictionary> dictionaries = Arrays.asList(jsonList);

            //提交到service
            //根据是否有传ID值 判断输入那类事务 null 新增 notnull 修改
            if (dictionaryCode.getId() != null) {
                //修改字典
                dictionaryService.updateDictionary(dictionaryCode,dictionaries);
            } else {
                //新增字典 带选项
                dictionaryService.addDictionary(dictionaryCode,dictionaries);
            }

        }

        Page<Dictionary> dictionaryList = dictionaryDao.findAllDictionary(index,10);
        model.addAttribute("dictionaryList", PageInfo.from(dictionaryList));
        //用于提示操作结果
        redirectAttributes.addFlashAttribute("message","操作成功!");
        return "redirect:/dictionary/list.html";
    }

}
