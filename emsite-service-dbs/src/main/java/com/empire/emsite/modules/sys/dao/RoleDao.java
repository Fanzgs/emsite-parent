/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.dao;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.sys.entity.Role;

/**
 * 类RoleDao.java的实现描述：角色DAO接口
 * 
 * @author arron 2017年10月30日 下午4:29:36
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

    public Role getByName(Role role);

    public Role getByEnname(Role role);

    /**
     * 维护角色与菜单权限关系
     * 
     * @param role
     * @return
     */
    public int deleteRoleMenu(Role role);

    public int insertRoleMenu(Role role);

    /**
     * 维护角色与公司部门关系
     * 
     * @param role
     * @return
     */
    public int deleteRoleOffice(Role role);

    public int insertRoleOffice(Role role);

}
