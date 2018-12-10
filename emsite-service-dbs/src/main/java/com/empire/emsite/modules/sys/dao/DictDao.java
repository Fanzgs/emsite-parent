/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.dao;

import java.util.List;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.sys.entity.Dict;

/**
 * 类DictDao.java的实现描述：字典DAO接口
 * 
 * @author arron 2017年10月30日 下午4:28:02
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

    public List<String> findTypeList(Dict dict);

}
