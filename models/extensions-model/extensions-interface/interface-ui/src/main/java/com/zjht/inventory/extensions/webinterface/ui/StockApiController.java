package com.zjht.inventory.extensions.webinterface.ui;

/**
 * Created by Jason on 2016/10/20.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjht.inventory.extensions.webinterface.ui.entity.*;
import com.zjht.inventory.stock.dao.ApplyDao;
import com.zjht.inventory.stock.entity.Apply;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * web外部申请接口
 */
@Controller
public class StockApiController {

    @ImportService
    ApplyDao applyDao;


    public static Logger logger = Logger.getLogger(StockApiController.class);
    /**
     {
     "id":"1",
     "orderId":"437379123111119990",
     "createBy":"1",
     "warehouseBelong":"1",
     "applyType":1,
     "remark":"test",
     "applyLists":[{
     "id":"1",
     "materielTypeId":"1",
     "materielId":"1",
     "applyId":"1",
     "count":20,
     "applyListProperties":[{
     "id":"1",
     "listId":"1",
     "property":"type",
     "value":"1"
     },{
     "id":"2",
     "listId":"1",
     "property":"sock",
     "value":"5"
     }]
     },{
     "id":"2",
     "materielTypeId":"1",
     "materielId":"1",
     "applyId":"1",
     "count":66,
     "applyListProperties":[{
     "id":"2",
     "listId":"2",
     "property":"type",
     "value":"1"
     }]
     }]
     }
     */



    /**
     * 新申请接口
     * @param apply
     * @param errors
     * @param request
     * @return
     */
    @RequestMapping(value = "/apply/api/add", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ApplyResponseEntity apply(@Valid @RequestBody Apply apply, Errors errors, HttpServletRequest request){
        logger.info("进入申请接口,收到以下参数 "+apply);
        if (errors.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : errors.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(";");
            }
            return new ApplyResponseEntity(ApplyResponseEntity.PARAMETER_ERROR);
        }




        logger.info("申请流程已完成 ");
        Map<String,Long> map =  new HashMap<>();
        map.put("pos机身",20l);
        map.put("密码键盘",11l);
        return new ApplyResponseEntity(ApplyResponseEntity.SUCCESS,"38788743111113",map);
    }


    /**
     * {
     *     "materielCategoryId":"1"
     * }
     */
    /**
     * 物料列表查询接口
     * @param requestEntity
     * @param errors
     * @param request
     * @return
     */
    @RequestMapping(value = "/query/api/materialList", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public QueryMaterialListResponseEntity materialList(@Valid @RequestBody QueryMaterialListRequestEntity requestEntity, Errors errors, HttpServletRequest request){
        logger.info("进入申请接口,收到以下参数"+requestEntity);
        if (errors.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : errors.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(";");
            }
            return new QueryMaterialListResponseEntity(ResponseCode.PARAMETER_ERROR);
        }


        logger.info("查询完成 ");
        MaterialEntity m = new MaterialEntity("1","code","name","type","yyyy-MM-dd HH:mm:ss");
        ArrayList<MaterialEntity> materialEntities = new ArrayList<>();
        materialEntities.add(m);
        return new QueryMaterialListResponseEntity(ResponseCode.SUCCESS,materialEntities);
    }




    /*
    *
    *   { "materialId":"1","properties":{"factory":"1","adapterModel":"2"} }
    * */
    @RequestMapping(value = "/query/api/stock", method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseBody
    public QueryStockResponseEntity stock(@Valid @RequestBody QueryStockRequestEntity requestEntity, Errors errors, HttpServletRequest request){
        logger.info("进入申请接口,收到以下参数"+requestEntity);
        if (errors.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : errors.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(";");
            }
            return new QueryStockResponseEntity(ResponseCode.PARAMETER_ERROR);
        }


        logger.info("查询完成 ");
        return new QueryStockResponseEntity(ResponseCode.SUCCESS,"20");
    }



    @RequestMapping(value = "/query/api/applyStatus", method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseBody
    public QueryApplyStatusResponseEntity status(@Valid @RequestBody QueryApplyStatusRequestEntity requestEntity, Errors errors, HttpServletRequest request){
        logger.info("进入申请接口,收到以下参数"+requestEntity);
        if (errors.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : errors.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(";");
            }
            return new QueryApplyStatusResponseEntity(ResponseCode.PARAMETER_ERROR);
        }
        Apply apply = applyDao.findById(1L);

        logger.info("查询完成 ");
        Long status = apply.getStatus();
        return new QueryApplyStatusResponseEntity(ResponseCode.SUCCESS, "", status.toString()
                ,status==1?"待处理":"已处理");
    }


    @RequestMapping(value = "/apply/api/callback", method = RequestMethod.POST,
            produces = {"application/json"})
    @ResponseBody
    public String callbackTest(@RequestBody ApplyCallbackEntity requestEntity, Errors errors, HttpServletRequest request){
        System.out.println("====================="+requestEntity+"=====================");
        return "{'result':'OK'}";
    }


    /**
     * 申请回调接口
     * @param entity 响应给外系统的实体
     */
    public static void applyCallback(ApplyCallbackEntity entity){
        HttpClient ie = new HttpClient();
        PostMethod post = new PostMethod("http://127.0.0.1:8080/apply/api/callback.html");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(entity);
            logger.info(json);
            post.setRequestEntity(new StringRequestEntity(json,"application/json","utf-8"));
            int i = ie.executeMethod(post);
            String result = post.getResponseBodyAsString();
            logger.info("\n\n\n回调响应代码: "+i+",    响应内容:"+result+"\n\n\n");
        } catch (JsonProcessingException e) {
            logger.error("回调接口转换JSON时遇到错误 ",e);
        } catch (UnsupportedEncodingException e) {
            logger.error("回调接口设置请求时遇到错误 ",e);
        } catch (IOException e) {
            logger.error("回调接口请求时遇到错误 ",e);
        }
    }
}
