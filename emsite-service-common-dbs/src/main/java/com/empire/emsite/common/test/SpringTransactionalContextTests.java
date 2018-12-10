/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 类SpringTransactionalContextTests.java的实现描述：Spring 单元测试基类
 * 
 * @author arron 2017年10月30日 下午4:05:30
 */
@ActiveProfiles("production")
@ContextConfiguration(locations = { "/spring-context.xml" })
public class SpringTransactionalContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    protected DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

}
