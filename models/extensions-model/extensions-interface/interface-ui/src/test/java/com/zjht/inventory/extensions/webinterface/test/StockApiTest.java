package com.zjht.inventory.extensions.webinterface.test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjht.inventory.extensions.webinterface.ui.StockApiController;
import com.zjht.inventory.extensions.webinterface.ui.entity.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Created by Jason on 2016/10/20.
 */
public class StockApiTest {


    @Test
    public void queryStockTest() throws UnsupportedEncodingException {
        HttpClient ie = new HttpClient();
        PostMethod post = new PostMethod("http://localhost:8080/apply/api/add.html");
        post.setRequestEntity(new StringRequestEntity(getJsonSuccess(), "application/json", "utf-8"));
        execute(ie, post);
    }

    @Test
    public void applyCallbackTest() throws UnsupportedEncodingException {
        String applyId = "1";
        String handleBy = "1";
        String handleResult = "1";
        String handleRemark = "哈哈";
        ApplyCallbackEntity entity = new ApplyCallbackEntity(applyId,handleBy,handleResult,handleRemark);
        LinkedHashMap<String, MaterialInfo> map = new LinkedHashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        MaterialInfo mi = new MaterialInfo(list);
        map.put("1", mi);

        entity.setMaterialInfoMap(map);
        StockApiController.applyCallback(entity);
    }

    private void execute(HttpClient ie, PostMethod post) {
        try {
            int i = ie.executeMethod(post);
            System.out.println("相应代码: "+i);
            String result = post.getResponseBodyAsString();
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("出错了 !");
        }
    }


    /**
     * 测试json
     */
    @Test
    public void testJacksonToBean() throws IOException {
        String json = getJsonSuccess();
        ApplyRequestEntity are = new ObjectMapper().readValue(json, ApplyRequestEntity.class);
        System.out.println(are);
    }


    /**
     * 测试json
     */
    @Test
    public void testJacksonToJson() throws IOException {
        JsonGenerator jg =
                new ObjectMapper().getJsonFactory().
                        createJsonGenerator(System.out, JsonEncoding.UTF8);


        ApplyEntity apply = new ApplyEntity("1","437379123111119990","1","1",1,"test");


        ArrayList<ApplyListEntity> als = new ArrayList<ApplyListEntity>();
        ApplyListEntity al1 = new ApplyListEntity("1","1","1","1",20);
        ApplyListEntity al2 = new ApplyListEntity("2","1","1","1",17);
        als.add(al1);
        als.add(al2);

        ArrayList<ApplyListPropertyEntity> alps = new ArrayList<ApplyListPropertyEntity>();
        ApplyListPropertyEntity alp1 = new ApplyListPropertyEntity("1","1","type","1");
        ApplyListPropertyEntity alp2 = new ApplyListPropertyEntity("2","1","face","2");
        alps.add(alp1);
        alps.add(alp2);

        ApplyRequestEntity are = new ApplyRequestEntity();
        are.setApply(apply);
        are.setApplyLists(als);
        are.setApplyListProperties(alps);

        jg.writeObject(are);
    }

    private String getJsonSuccess() {
        String json = "{\"apply\":{\"id\":\"1\",\"orderId\":\"437379123111119990\",\"createBy\":\"1\",\"warehouseBelong\":\"1\",\"applyType\":17,\"remark\":\"test\"},\"applyLists\":[{\"id\":\"1\",\"materielTypeId\":\"1\",\"materielId\":\"1\",\"applyId\":\"1\",\"count\":20},{\"id\":\"2\",\"materielTypeId\":\"1\",\"materielId\":\"1\",\"applyId\":\"1\",\"count\":17}],\"applyListProperties\":[{\"id\":\"1\",\"listId\":\"1\",\"property\":\"type\",\"value\":\"1\"},{\"id\":\"2\",\"listId\":\"1\",\"property\":\"face\",\"value\":\"2\"}]}\n";
        return json;
    }

    private String getJsonError() {
        String json = "{\n" +
                "\n" +
                "                                        \"apply\":{\n" +
                "                                            \"id\":\"1\",\n" +
                "                                            \"orderId\":\"1\",\n" +
                "                                            \"createBy\":\"1\",\n" +
                "                                            \"warehouseBelong\":\"1\",\n" +
                "                                            \"applyType\":\"e\",\n" +
                "                                            \"remark\":\"1\"\n" +
                "                                        },\n" +
                "                                        \"applyLists\":[\n" +
                "                                            {\n" +
                "                                                \"id\":\"1\",\n" +
                "                                                \"materielTypeId\":\"1\",\n" +
                "                                                \"materielId\":\"1\",\n" +
                "                                                \"applyId\":\"1\",\n" +
                "                                                \"count\":\"19\"\n" +
                "                                            },\n" +
                "                                            {\n" +
                "                                                \"id\":\"2\",\n" +
                "                                                \"materielTypeId\":\"1\",\n" +
                "                                                \"materielId\":\"1\",\n" +
                "                                                \"applyId\":\"1\",\n" +
                "                                                \"count\":\"27\"\n" +
                "                                            }\n" +
                "                                        ],\n" +
                "                                        \"applyListProperties\":[\n" +
                "                                            {\n" +
                "                                                \"id\":\"1\",\n" +
                "                                                \"listId\":\"1\",\n" +
                "                                                \"property\":\"model\",\n" +
                "                                                \"value\":\"P30\"\n" +
                "                                            },\n" +
                "                                            {\n" +
                "                                                \"id\":\"2\",\n" +
                "                                                \"listId\":\"2\",\n" +
                "                                                \"property\":\"factory\",\n" +
                "                                                \"value\":\"联想\"\n" +
                "                                            }\n" +
                "                                        ]\n" +
                "\n" +
                "                                    }";
        return json;
    }
}
