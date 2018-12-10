/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.dao;

import com.empire.emsite.common.persistence.TreeDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.test.entity.TestTree;

/**
 * 类TestTreeDao.java的实现描述：树结构生成DAO接口
 * 
 * @author arron 2017年10月30日 下午4:39:05
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {

}
