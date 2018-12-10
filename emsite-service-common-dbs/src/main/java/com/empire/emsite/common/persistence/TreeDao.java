/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.persistence;

import java.util.List;

/**
 * 类TreeDao.java的实现描述：DAO支持类实现
 * 
 * @author arron 2017年10月30日 下午3:54:36
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

    /**
     * 找到所有子节点
     * 
     * @param entity
     * @return
     */
    public List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * 
     * @param entity
     * @return
     */
    public int updateParentIds(T entity);

}
