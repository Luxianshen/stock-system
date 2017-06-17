package com.zjht.inventory.extensions.webinterface.ui.entity;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2016/10/20.
 */
public class AbstractResponseEntity extends ResponseCode {

    /**
     *
     * @param responseCode
     */
    public AbstractResponseEntity(String responseCode) {
        this.responseCode = responseCode;
        this.responseDesc = DESC.get(responseCode);
        this.responseTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     *
     * @param responseCode
     * @param prompt
     */
    public AbstractResponseEntity(String responseCode, String prompt) {
        this.responseCode = responseCode;
        this.responseDesc = DESC.get(responseCode);
        this.responsePrompt = prompt;
        this.responseTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 响应代码
     */
    private String responseCode;
    /**
     * 代码简述
     */
    private String responseDesc;
    /**
     * 操作提示
     */
    private String responsePrompt;
    /**
     * 响应时间
     */
    private String responseTime;

    @Override
    public String toString() {
        return "AbstractResponseEntity{" +
                "responseCode='" + responseCode + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                ", responsePrompt='" + responsePrompt + '\'' +
                ", responseTime='" + responseTime + '\'' +
                '}';
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponsePrompt() {
        return responsePrompt;
    }

    public void setResponsePrompt(String responsePrompt) {
        this.responsePrompt = responsePrompt;
    }

    public String getResponseTime() {
        if(StringUtils.isEmpty(responseTime)) {
            this.responseTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        }
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}
