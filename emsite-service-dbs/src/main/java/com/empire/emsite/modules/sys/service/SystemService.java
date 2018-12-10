/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.security.Digests;
import com.empire.emsite.common.service.BaseService;
import com.empire.emsite.common.service.ServiceException;
import com.empire.emsite.common.utils.Encodes;
import com.empire.emsite.common.utils.StringUtils;
import com.empire.emsite.modules.sys.dao.MenuDao;
import com.empire.emsite.modules.sys.dao.RoleDao;
import com.empire.emsite.modules.sys.dao.UserDao;
import com.empire.emsite.modules.sys.entity.Menu;
import com.empire.emsite.modules.sys.entity.Office;
import com.empire.emsite.modules.sys.entity.Role;
import com.empire.emsite.modules.sys.entity.User;
//import com.empire.emsite.common.security.shiro.session.SessionDAO;
import com.empire.emsite.modules.sys.utils.UserDbsUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//import com.empire.emsite.modules.sys.security.SystemAuthorizingRealm;
/**
 * 类SystemService.java的实现描述：系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * 
 * @author arron 2017年10月30日 下午4:34:47
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {

    public static final String HASH_ALGORITHM   = "SHA-1";
    public static final int    HASH_INTERATIONS = 1024;
    public static final int    SALT_SIZE        = 8;

    @Autowired
    private UserDao            userDao;
    @Autowired
    private RoleDao            roleDao;
    @Autowired
    private MenuDao            menuDao;

    //@Autowired
    //private SessionDAO sessionDao;
    /*
     * @Autowired private SystemAuthorizingRealm systemRealm;
     */

    /*
     * public SessionDAO getSessionDao() { return sessionDao; }
     */

    /*
     * @Autowired private IdentityService identityService;
     */

    //-- User Service --//

    /**
     * 获取用户
     * 
     * @param id
     * @return
     */
    public User getUser(String id) {
        return userDao.get(id);
        //return UserUtils.get(id);
    }

    /**
     * 根据登录名获取用户
     * 
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName) {
        return UserDbsUtils.getByLoginName(loginName);
    }

    public Page<User> findUser(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        //page.setList(userDao.findList(user));
        //集成PageHelper分页
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        PageInfo<User> userList=new PageInfo<User>(userDao.findList(user));
        page.setPageInfo(userList);
        return page;
    }

    /**
     * 无分页查询人员列表
     * 
     * @param user
     * @return
     */
    public List<User> findUser(User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        List<User> list = userDao.findList(user);
        return list;
    }

    /**
     * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
     * 
     * @param user
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> findUserByOfficeId(String officeId) {
        List<User> list = null;
        //(List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
        if (list == null) {
            User user = new User();
            user.setOffice(new Office(officeId));
            list = userDao.findUserByOfficeId(user);
            //CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
        }
        return list;
    }

    @Transactional(readOnly = false)
    public void saveUser(User user) {
        if (StringUtils.isBlank(user.getId())) {
            user.preInsert();
            userDao.insert(user);
        } else {
            // 清除原用户机构用户缓存
            User oldUser = userDao.get(user.getId());
            /*
             * if (oldUser.getOffice() != null && oldUser.getOffice().getId() !=
             * null){ CacheUtils.remove(UserUtils.USER_CACHE,
             * UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ +
             * oldUser.getOffice().getId()); }
             */
            // 更新用户数据
            user.preUpdate();
            userDao.update(user);
        }
        if (StringUtils.isNotBlank(user.getId())) {
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            if (user.getRoleList() != null && user.getRoleList().size() > 0) {
                userDao.insertUserRole(user);
            } else {
                throw new ServiceException(user.getLoginName() + "没有设置角色！");
            }
            // 将当前用户同步到Activiti
            //saveActivitiUser(user);
            // 清除用户缓存
            //UserUtils.clearCache(user);
            //			// 清除权限缓存
            //systemRealm.clearAllCachedAuthorizationInfo();
        }
    }

    @Transactional(readOnly = false)
    public void updateUserInfo(User user) {
        user.preUpdate();
        userDao.updateUserInfo(user);
        // 清除用户缓存
        //UserUtils.clearCache(user);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        userDao.delete(user);
        // 同步到Activiti
        //deleteActivitiUser(user);
        // 清除用户缓存
        //UserUtils.clearCache(user);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void updatePasswordById(String id, String loginName, String newPassword) {
        User user = new User(id);
        user.setPassword(entryptPassword(newPassword));
        userDao.updatePasswordById(user);
        // 清除用户缓存
        user.setLoginName(loginName);
        //UserUtils.clearCache(user);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void updateUserLoginInfo(User user) {
        // 保存上次登录信息
        // 更新本次登录信息
        userDao.updateLoginInfo(user);
    }

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * 
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
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

    public Role getRole(String id) {
        return roleDao.get(id);
    }

    public Role getRoleByName(String name) {
        Role r = new Role();
        r.setName(name);
        return roleDao.getByName(r);
    }

    public Role getRoleByEnname(String enname) {
        Role r = new Role();
        r.setEnname(enname);
        return roleDao.getByEnname(r);
    }

    public List<Role> findRole(Role role) {
        return roleDao.findList(role);
    }

    public List<Role> findAllRole(User user) {
        List<Role> roleList = null;
        if (user.isAdmin()) {
            roleList = roleDao.findAllList(new Role());
        } else {
            Role role = new Role();
            role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "o", "u"));
            roleList = roleDao.findList(role);
        }
        return roleList;
        //return UserUtils.getRoleList();
    }

    @Transactional(readOnly = false)
    public void saveRole(Role role) {
        if (StringUtils.isBlank(role.getId())) {
            role.preInsert();
            roleDao.insert(role);
            // 同步到Activiti
            //saveActivitiGroup(role);
        } else {
            role.preUpdate();
            roleDao.update(role);
        }
        // 更新角色与菜单关联
        roleDao.deleteRoleMenu(role);
        if (role.getMenuList().size() > 0) {
            roleDao.insertRoleMenu(role);
        }
        // 更新角色与部门关联
        roleDao.deleteRoleOffice(role);
        if (role.getOfficeList().size() > 0) {
            roleDao.insertRoleOffice(role);
        }
        // 同步到Activiti
        //saveActivitiGroup(role);
        // 清除用户角色缓存
        //UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void deleteRole(Role role) {
        roleDao.delete(role);
        // 同步到Activiti
        //deleteActivitiGroup(role);
        // 清除用户角色缓存
        //	UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public Boolean outUserInRole(Role role, User user) {
        List<Role> roles = user.getRoleList();
        for (Role e : roles) {
            if (e.getId().equals(role.getId())) {
                roles.remove(e);
                saveUser(user);
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = false)
    public User assignUserToRole(Role role, User user) {
        if (user == null) {
            return null;
        }
        List<String> roleIds = user.getRoleIdList();
        if (roleIds.contains(role.getId())) {
            return null;
        }
        user.getRoleList().add(role);
        saveUser(user);
        return user;
    }

    //-- Menu Service --//

    public Menu getMenu(String id) {
        return menuDao.get(id);
    }

    public List<Menu> findAllMenu(User user) {
        List<Menu> menuList = null;
        if (user.isAdmin()) {
            menuList = menuDao.findAllList(new Menu());
        } else {
            Menu m = new Menu();
            m.setUserId(user.getId());
            menuList = menuDao.findByUserId(m);
        }
        return menuList;
    }

    public List<Menu> findAllMenu() {
        List<Menu> menuList = menuDao.findAllList(new Menu());
        return menuList;
    }

    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {

        // 获取父节点实体
        menu.setParent(this.getMenu(menu.getParent().getId()));

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = menu.getParentIds();

        // 设置新的父节点串
        menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId() + ",");

        // 保存或更新实体
        if (StringUtils.isBlank(menu.getId())) {
            menu.preInsert();
            menuDao.insert(menu);
        } else {
            menu.preUpdate();
            menuDao.update(menu);
        }

        // 更新子节点 parentIds
        Menu m = new Menu();
        m.setParentIds("%," + menu.getId() + ",%");
        List<Menu> list = menuDao.findByParentIdsLike(m);
        for (Menu e : list) {
            e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
            menuDao.updateParentIds(e);
        }
        // 清除用户菜单缓存
        //	UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        //CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void updateMenuSort(Menu menu) {
        menuDao.updateSort(menu);
        // 清除用户菜单缓存
        //	UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        //	CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void deleteMenu(Menu menu) {
        menuDao.delete(menu);
        // 清除用户菜单缓存
        //	UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
        //		// 清除权限缓存
        //		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        //	CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    /**
     * 获取Key加载信息
     */
    //    public static boolean printKeyLoadMessage() {
    //        StringBuilder sb = new StringBuilder();
    //        sb.append("\r\n======================================================================\r\n");
    //        sb.append("\r\n    欢迎使用 " + Global.getConfig("productName") + "  - Powered By http://emsite.com\r\n");
    //        sb.append("\r\n======================================================================\r\n");
    //        System.out.println(sb.toString());
    //        return true;
    //    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

}
