/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.modules.sys.dao.DictDao;
import com.empire.emsite.modules.sys.entity.Dict;

/**
 * 类DictService.java的实现描述：字典Service
 * 
 * @author arron 2017年10月30日 下午4:33:31
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

    /**
     * 查询字段类型列表
     * 
     * @return
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new Dict());
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);
    }

    public List<Dict> findAllList() {
        return dao.findAllList(new Dict());
    }

}
