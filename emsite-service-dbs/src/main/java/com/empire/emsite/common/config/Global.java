/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.config;

import java.util.Map;

import com.empire.emsite.common.utils.PropertiesLoader;
import com.empire.emsite.common.utils.StringUtils;
//import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;

/**
 * 类Global.java的实现描述：全局配置类
 * 
 * @author arron 2017年10月30日 下午3:51:28
 */
public class Global {

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader    loader = new PropertiesLoader("emsite.properties");
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map    = Maps.newHashMap();

    /**
     * 获取配置
     * 
     * @see ${fns:getConfig('adminPath')}
     */
    private static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取配置:jdbc类型配置
     */
    public static String getJdbcType() {
        return getConfig("jdbc.type");
    }

    /**
     * 获取配置:工程路径配置
     */
    public static String getProjectPath() {
        return getConfig("projectPath");
    }
}
