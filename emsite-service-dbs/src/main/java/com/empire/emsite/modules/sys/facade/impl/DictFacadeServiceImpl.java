/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.sys.entity.Dict;
import com.empire.emsite.modules.sys.facade.DictFacadeService;
import com.empire.emsite.modules.sys.service.DictService;

/**
 * 类DictFacadeServiceImpl.java的实现描述：字典FacadeService接口实现
 * 
 * @author arron 2017年9月17日 下午9:53:48
 */
@Service
public class DictFacadeServiceImpl implements DictFacadeService {
    @Autowired
    private DictService dictService;

    @Override
    public List<String> findTypeList() {
        return dictService.findTypeList();
    }

    @Override
    public void save(Dict dict) {
        dictService.save(dict);
    }

    @Override
    public void delete(Dict dict) {
        dictService.delete(dict);
    }

    @Override
    public Dict get(String id) {
        return dictService.get(id);
    }

    @Override
    public Page<Dict> findPage(Page<Dict> page, Dict dict) {
        return dictService.findPage(page, dict);
    }

    @Override
    public List<Dict> findList(Dict dict) {
        return dictService.findList(dict);
    }

    @Override
    public List<Dict> findAllList() {
        return dictService.findAllList();
    }

}
