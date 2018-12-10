/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.sys.entity.Menu;
import com.empire.emsite.modules.sys.entity.Role;
import com.empire.emsite.modules.sys.entity.User;
import com.empire.emsite.modules.sys.facade.SystemFacadeService;
import com.empire.emsite.modules.sys.service.SystemService;

/**
 * 类SystemFacadeServiceImpl.java的实现描述：系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * 
 * @author arron 2017年9月17日 下午9:55:57
 */
@Service
public class SystemFacadeServiceImpl implements SystemFacadeService {
    @Autowired
    private SystemService systemService;

    /**
     * 获取用户
     * 
     * @param id
     * @return
     */
    @Override
    public User getUser(String id) {
        return systemService.getUser(id);
    }

    /**
     * 根据登录名获取用户
     * 
     * @param loginName
     * @return
     */
    @Override
    public User getUserByLoginName(String loginName) {
        return systemService.getUserByLoginName(loginName);
    }

    @Override
    public Page<User> findUser(Page<User> page, User user) {
        return systemService.findUser(page, user);
    }

    /**
     * 无分页查询人员列表
     * 
     * @param user
     * @return
     */
    @Override
    public List<User> findUser(User user) {
        return systemService.findUser(user);
    }

    /**
     * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
     * 
     * @param user
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUserByOfficeId(String officeId) {
        return systemService.findUserByOfficeId(officeId);
    }

    @Override
    public void saveUser(User user) {
        systemService.saveUser(user);
    }

    @Override
    public void updateUserInfo(User user) {
        systemService.updateUserInfo(user);
    }

    @Override
    public void deleteUser(User user) {
        systemService.deleteUser(user);
    }

    @Override
    public void updatePasswordById(String id, String loginName, String newPassword) {
        systemService.updatePasswordById(id, loginName, newPassword);
        ;
    }

    @Override
    public void updateUserLoginInfo(User user) {
        systemService.updateUserLoginInfo(user);
    }

    /**
     * 获得活动会话
     * 
     * @return
     */
    /*
     * public Collection<Session> getActiveSessions(){ return
     * sessionDao.getActiveSessions(false); }
     */

    //-- Role Service --//

    @Override
    public Role getRole(String id) {
        return systemService.getRole(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return systemService.getRoleByName(name);
    }

    @Override
    public Role getRoleByEnname(String enname) {
        return systemService.getRoleByEnname(enname);
    }

    @Override
    public List<Role> findRole(Role role) {
        return systemService.findRole(role);
    }

    @Override
    public List<Role> findAllRole(User user) {
        return systemService.findAllRole(user);
    }

    @Override
    public void saveRole(Role role) {
        systemService.saveRole(role);
    }

    @Override
    public void deleteRole(Role role) {
        systemService.deleteRole(role);
    }

    @Override
    public Boolean outUserInRole(Role role, User user) {
        return systemService.outUserInRole(role, user);
    }

    @Override
    public User assignUserToRole(Role role, User user) {
        return systemService.assignUserToRole(role, user);
    }

    //-- Menu Service --//
    @Override
    public Menu getMenu(String id) {
        return systemService.getMenu(id);
    }

    @Override
    public List<Menu> findAllMenu(User user) {
        return systemService.findAllMenu(user);
    }

    @Override
    public List<Menu> findAllMenu() {
        return systemService.findAllMenu();
    }

    @Override
    public void saveMenu(Menu menu) {
        systemService.saveMenu(menu);
    }

    @Override
    public void updateMenuSort(Menu menu) {
        systemService.updateMenuSort(menu);
    }

    @Override
    public void deleteMenu(Menu menu) {
        systemService.deleteMenu(menu);
    }
}
