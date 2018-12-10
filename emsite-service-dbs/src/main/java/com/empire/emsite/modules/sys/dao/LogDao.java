/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.sys.entity.Log;

/**
 * 类LogDao.java的实现描述：日志DAO接口
 * 
 * @author arron 2017年10月30日 下午4:28:49
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
