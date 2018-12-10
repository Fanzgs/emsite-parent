/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.dao;

import com.empire.emsite.common.persistence.TreeDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.sys.entity.Office;

/**
 * 类OfficeDao.java的实现描述：机构DAO接口
 * 
 * @author arron 2017年10月30日 下午4:29:21
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

}
