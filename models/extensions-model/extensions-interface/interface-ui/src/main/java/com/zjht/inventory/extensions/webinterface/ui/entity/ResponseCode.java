package com.zjht.inventory.extensions.webinterface.ui.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2016/10/20.
 */
public class ResponseCode {
    //成功
    public static final String SUCCESS = "200";
    //参数错误
    public static final String PARAMETER_ERROR = "444";
    //关联关系错误
    public static final String ASSOCIATION_ERROR = "445";
    //服务器错误
    public static final String SERVER_ERROR = "500";
    //库存不足
    public static final String EXCESSINVENTORYORSHORTAGE_ERROR = "666";
    //未知错误
    public static final String UNKNOWN_ERROR = "999";


    /**
     * 错误代码描简述
     */
    public static final Map<String,String> DESC = new HashMap<>();

    static{
        DESC.put("200","成功");
        DESC.put("444","参数错误");
        DESC.put("445","关联关系错误");
        DESC.put("500","服务器错误");
        DESC.put("666","库存不足");
        DESC.put("999","未知错误");
    }
}
