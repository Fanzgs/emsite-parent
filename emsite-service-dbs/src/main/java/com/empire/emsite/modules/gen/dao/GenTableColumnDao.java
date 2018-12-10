/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.gen.entity.GenTableColumn;

/**
 * 类GenTableColumnDao.java的实现描述：业务表字段DAO接口
 * 
 * @author arron 2017年10月30日 下午4:14:03
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {

    public void deleteByGenTableId(String genTableId);
}
