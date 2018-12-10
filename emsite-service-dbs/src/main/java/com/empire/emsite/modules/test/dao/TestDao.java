/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.test.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.test.entity.Test;

/**
 * 类TestDao.java的实现描述：测试DAO接口
 * 
 * @author arron 2017年10月30日 下午4:35:41
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {

}
