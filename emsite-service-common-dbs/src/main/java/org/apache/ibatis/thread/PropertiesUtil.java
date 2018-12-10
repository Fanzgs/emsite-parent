/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package org.apache.ibatis.thread;

import java.util.Properties;

/**
 * 类PropertiesUtil.java的实现描述：TODO 类实现描述
 * 
 * @author arron 2017年10月30日 下午4:44:11
 */
public class PropertiesUtil {

    private static String     filename = "/mybatis-refresh.properties";
    private static Properties pro      = new Properties();
    static {
        try {
            pro.load(PropertiesUtil.class.getResourceAsStream(filename));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Load mybatis-refresh “" + filename + "” file error.");
        }
    }

    public static int getInt(String key) {
        int i = 0;
        try {
            i = Integer.parseInt(getString(key));
        } catch (Exception e) {
        }
        return i;
    }

    public static String getString(String key) {
        return pro == null ? null : pro.getProperty(key);
    }

}
