/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.unj.dubbotest.provider.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类Provider.java的实现描述：TODO 类实现描述
 * 
 * @author arron 2017年10月30日 下午4:42:33
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        context.start();
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
