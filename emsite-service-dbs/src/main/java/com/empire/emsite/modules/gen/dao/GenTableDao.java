/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.gen.entity.GenTable;

/**
 * 类GenTableDao.java的实现描述：业务表DAO接口
 * 
 * @author arron 2017年10月30日 下午4:14:13
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {

}
