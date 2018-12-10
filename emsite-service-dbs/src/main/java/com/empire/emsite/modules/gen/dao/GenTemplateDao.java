/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.gen.entity.GenTemplate;

/**
 * 类GenTemplateDao.java的实现描述：代码模板DAO接口
 * 
 * @author arron 2017年10月30日 下午4:14:22
 */
@MyBatisDao
public interface GenTemplateDao extends CrudDao<GenTemplate> {

}
