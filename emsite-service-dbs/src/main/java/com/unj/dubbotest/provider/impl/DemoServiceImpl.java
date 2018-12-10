/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.unj.dubbotest.provider.impl;

import java.util.ArrayList;
import java.util.List;

import com.unj.dubbotest.provider.DemoService;

/**
 * 类DemoServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author arron 2017年10月30日 下午4:42:16
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public List getUsers() {
        List list = new ArrayList();
        User u1 = new User();
        u1.setName("jack");
        u1.setAge(20);
        u1.setSex("m");

        User u2 = new User();
        u2.setName("tom");
        u2.setAge(21);
        u2.setSex("m");

        User u3 = new User();
        u3.setName("rose");
        u3.setAge(19);
        u3.setSex("w");

        list.add(u1);
        list.add(u2);
        list.add(u3);
        return list;
    }
}
