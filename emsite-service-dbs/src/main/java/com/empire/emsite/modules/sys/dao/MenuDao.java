/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.dao;

import java.util.List;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.sys.entity.Menu;

/**
 * 类MenuDao.java的实现描述：菜单DAO接口
 * 
 * @author arron 2017年10月30日 下午4:29:04
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

    public List<Menu> findByParentIdsLike(Menu menu);

    public List<Menu> findByUserId(Menu menu);

    public int updateParentIds(Menu menu);

    public int updateSort(Menu menu);

}
