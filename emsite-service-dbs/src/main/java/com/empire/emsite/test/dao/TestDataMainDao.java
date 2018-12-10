/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.test.entity.TestDataMain;

/**
 * 类TestDataMainDao.java的实现描述：主子表生成DAO接口
 * 
 * @author arron 2017年10月30日 下午4:38:54
 */
@MyBatisDao
public interface TestDataMainDao extends CrudDao<TestDataMain> {

}
