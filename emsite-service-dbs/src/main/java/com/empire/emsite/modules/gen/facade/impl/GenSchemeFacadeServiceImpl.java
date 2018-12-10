/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.gen.entity.GenScheme;
import com.empire.emsite.modules.gen.facade.GenSchemeFacadeService;
import com.empire.emsite.modules.gen.service.GenSchemeService;
import com.empire.emsite.modules.sys.entity.User;

/**
 * 类GenSchemeFacadeServiceImpl.java的实现描述： 生成方案FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:49:02
 */
@Service
public class GenSchemeFacadeServiceImpl implements GenSchemeFacadeService {
    @Autowired
    private GenSchemeService genSchemeService;

    @Override
    public GenScheme get(String id) {
        return genSchemeService.get(id);
    }

    @Override
    public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
        return genSchemeService.find(page, genScheme);
    }

    @Override
    public String save(GenScheme genScheme, User user) {
        return genSchemeService.save(genScheme, user);
    }

    @Override
    public void delete(GenScheme genScheme) {
        genSchemeService.delete(genScheme);
    }

}
