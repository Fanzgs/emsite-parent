/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.dao;

import java.util.List;

import com.empire.emsite.common.persistence.CrudDao;
import com.empire.emsite.common.persistence.annotation.MyBatisDao;
import com.empire.emsite.modules.gen.entity.GenTable;
import com.empire.emsite.modules.gen.entity.GenTableColumn;

/**
 * 类GenDataBaseDictDao.java的实现描述：业务表字段DAO接口
 * 
 * @author arron 2017年10月30日 下午4:13:38
 */
@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {

    /**
     * 查询表列表
     * 
     * @param genTable
     * @return
     */
    List<GenTable> findTableList(GenTable genTable);

    /**
     * 获取数据表字段
     * 
     * @param genTable
     * @return
     */
    List<GenTableColumn> findTableColumnList(GenTable genTable);

    /**
     * 获取数据表主键
     * 
     * @param genTable
     * @return
     */
    List<String> findTablePK(GenTable genTable);

}
