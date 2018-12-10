/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.test.entity.TestData;

/**
 * 类TestDataDao.java的实现描述：单表生成DAO接口
 * 
 * @author arron 2017年10月30日 下午4:38:42
 */
@MyBatisDao
public interface TestDataDao extends CrudDao<TestData> {

}
