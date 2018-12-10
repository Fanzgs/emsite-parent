/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.gen.entity.GenConfig;
import com.empire.emsite.modules.gen.entity.GenTable;
import com.empire.emsite.modules.gen.facade.GenTableFacadeService;
import com.empire.emsite.modules.gen.service.GenTableService;
import com.empire.emsite.modules.gen.util.GenDbsUtils;

/**
 * 类GenTableFacadeServiceImpl.java的实现描述：业务表FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:49:45
 */
@Service
public class GenTableFacadeServiceImpl implements GenTableFacadeService {
    @Autowired
    private GenTableService genTableService;

    @Override
    public GenTable get(String id) {
        return genTableService.get(id);
    }

    @Override
    public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
        return genTableService.find(page, genTable);
    }

    @Override
    public List<GenTable> findAll() {
        return genTableService.findAll();
    }

    /**
     * 获取物理数据表列表
     * 
     * @param genTable
     * @return
     */
    @Override
    public List<GenTable> findTableListFormDb(GenTable genTable) {
        return genTableService.findTableListFormDb(genTable);
    }

    /**
     * 验证表名是否可用，如果已存在，则返回false
     * 
     * @param genTable
     * @return
     */
    @Override
    public boolean checkTableName(String tableName) {
        return genTableService.checkTableName(tableName);
    }

    /**
     * 获取物理数据表列表
     * 
     * @param genTable
     * @return
     */
    @Override
    public GenTable getTableFormDb(GenTable genTable) {
        return genTableService.getTableFormDb(genTable);
    }

    @Override
    public void save(GenTable genTable) {
        genTableService.save(genTable);
    }

    @Override
    public void delete(GenTable genTable) {
        genTableService.delete(genTable);
    }

    @Override
    public GenConfig getGenConfig() {
        return GenDbsUtils.getConfig();
    }
}
