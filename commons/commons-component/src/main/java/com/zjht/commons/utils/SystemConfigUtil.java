package com.zjht.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统配置文件工具，自动加载classpath:config.properties 文件
 * Created by leaves chen<leaves615@gmail.com> on 15/7/1.
 */
public class SystemConfigUtil {
    private static final Log log = LogFactory.getLog(SystemConfigUtil.class);

    private static final String RESOURCE_PATH = "classpath:config.properties";

    private static SystemConfigUtil instance;

    private Properties prop = new Properties();

    public static SystemConfigUtil getInstance() {
        if (instance == null) {
            synchronized (SystemConfigUtil.class) {
                if (instance == null) {
                    instance = new SystemConfigUtil();
                }
            }
        }
        return instance;
    }

    private SystemConfigUtil() {
        ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resourceLoader.getResources(RESOURCE_PATH);
            for (Resource resource : resources) {
                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                CollectionUtils.mergePropertiesIntoMap(properties, prop);
            }
        } catch (IOException e) {
            log.error("load config failed", e);
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public String getProperty(String key, String defalutValue) {
        return prop.getProperty(key, defalutValue);
    }

    public int getPropertyAsInt(String key) {
        return getPropertyAsInt(key, 0);
    }

    public int getPropertyAsInt(String key, int defalutValue) {
        String value = getProperty(key, null);
        if (!StringUtils.isEmpty(value)) {
            return Integer.parseInt(value);
        }else{
            return defalutValue;
        }
    }

    public long getPropertyAsLong(String key) {
        return getPropertyAsLong(key, 0L);
    }


    public long getPropertyAsLong(String key, long defaultValue) {
        String value = getProperty(key, null);
        if (!StringUtils.isEmpty(value)) {
            return Integer.parseInt(value);
        }else{
            return defaultValue;
        }
    }
}
