/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.testwx.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.testwx.entity.TestWxData;

/**
 * 类TestWxDataDao.java的实现描述：bbDAO接口
 * 
 * @author arron 2017年10月30日 下午4:37:07
 */
@MyBatisDao
public interface TestWxDataDao extends CrudDao<TestWxData> {

}
